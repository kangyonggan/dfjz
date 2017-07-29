package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.CategoryService;
import com.kangyonggan.app.dfjz.biz.service.RepositoryService;
import com.kangyonggan.app.dfjz.common.IOUtil;
import com.kangyonggan.app.dfjz.common.XmlUtil;
import com.kangyonggan.app.dfjz.model.dto.AutoReplyRequestDto;
import com.kangyonggan.app.dfjz.model.vo.Category;
import com.kangyonggan.app.dfjz.model.vo.Repository;
import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author kangyonggan
 * @since 7/28/17
 */
@Service
@Log4j2
public class WXService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 文本信息模板
     */
    private static String TEXT_XML_TEMPLATE = "<xml>" +
            "<ToUserName><![CDATA[%s]]></ToUserName>" +
            "<FromUserName><![CDATA[%s]]></FromUserName>" +
            "<CreateTime>%d</CreateTime>" +
            "<MsgType><![CDATA[text]]></MsgType>" +
            "<Content><![CDATA[%s]]></Content>" +
            "</xml>";

    /**
     * 图文信息模板
     */
    private static String NEWS_XML_TEMPLATE = "<xml>\n" +
            "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
            "<CreateTime>%d</CreateTime>\n" +
            "<MsgType><![CDATA[news]]></MsgType>\n" +
            "<ArticleCount>1</ArticleCount>\n" +
            "<Articles>\n" +
            "<item>\n" +
            "<Title><![CDATA[%s]]></Title> \n" +
            "<Description><![CDATA[%s]]></Description>\n" +
            "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
            "<Url><![CDATA[%s]]></Url>\n" +
            "</item>\n" +
            "</Articles>\n" +
            "</xml>";

    /**
     * 解析请求
     *
     * @param request
     * @return
     */
    public AutoReplyRequestDto getRequestDtoFromRequest(HttpServletRequest request) {
        AutoReplyRequestDto requestDto = new AutoReplyRequestDto();

        // 获取请求报文
        String xml;
        try {
            xml = IOUtil.read(request.getInputStream());
            log.info("接收到的xml：{}", xml);
        } catch (IOException e) {
            log.warn("读取输入流异常", e);
            return null;
        }

        Document doc;
        try {
            doc = XmlUtil.parseText(xml);
            log.info("xml解析成功");
        } catch (DocumentException e) {
            log.warn("xml解析异常", e);
            return null;
        }

        try {
            Element root = doc.getRootElement();
            String toUserName = root.element("ToUserName").getTextTrim();// 开发者微信号
            String fromUserName = root.element("FromUserName").getTextTrim();// 发送方帐号（一个OpenID）
            String createTime = root.element("CreateTime").getTextTrim();// 消息创建时间 （整型）
            String msgType = root.element("MsgType").getTextTrim();// text
            if ("text".equals(msgType)) {
                String content = root.element("Content").getTextTrim();// 文本消息内容
                requestDto.setContent(content);
            } else if ("image".equals(msgType)) {
                String picUrl = root.element("PicUrl").getTextTrim();// 图片url
                requestDto.setPicUrl(picUrl);
            } else if ("voice".equals(msgType)) {
                // TODO 语言消息
            }
            String msgId = root.element("MsgId").getTextTrim();// 消息id，64位整型

            requestDto.setMsgId(msgId);
            requestDto.setToUserName(toUserName);
            requestDto.setMsgType(msgType);
            requestDto.setFromUserName(fromUserName);
            requestDto.setCreateTime(createTime);
        } catch (Exception e) {
            log.warn("提取requestDto异常", e);
            return null;
        }

        return requestDto;
    }

    /**
     * 组装响应报文
     *
     * @param requestDto
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String getResponseXml(AutoReplyRequestDto requestDto) {
        if (!requestDto.getMsgType().equals("text")) {
            return buildTextMsg(requestDto, "我暂时只能看懂文字，更强大的功能小胖正在开发，敬请期待吧！");
        }

        String content = requestDto.getContent();
        String respXml;

        List<Category> categories = categoryService.findAllCategories();
        int index = getCategoryIndex(content, categories.size());

        if (index > -1) {
            Category category = categories.get(index);
            respXml = String.format(NEWS_XML_TEMPLATE, requestDto.getFromUserName(), requestDto.getToUserName(), System.currentTimeMillis(), category.getName(), category.getDescription(), "https://kangyonggan.com" + category.getPicture(), "https://kangyonggan.com/#category/" + category.getCode());
        } else if ("博客".equals(content)) {
            respXml = buildTextMsg(requestDto, getMenus());
        } else if (content.trim().startsWith("教你:") || content.trim().startsWith("教你：")) {
            content = content.substring(3);
            String arr[] = content.split("==");
            if (arr != null && arr.length == 2) {
                if (repositoryService.existQuestion(arr[0])) {
                    Repository repository = new Repository();
                    repository.setQuestion(arr[0]);
                    repositoryService.updateRepositoryWeight(repository);
                } else {
                    Repository repository = new Repository();
                    repository.setQuestion(arr[0]);
                    repository.setAnswer(arr[1]);
                    repositoryService.saveRepository(repository);
                }

                respXml = buildTextMsg(requestDto, "我已经学习成功了");
            } else {
                return buildTextMsg(requestDto, "你输入的格式不对，我无法学习，请重新尝试！");
            }
        } else {
            // 智能小胖
            respXml = getXiaoPangResp(requestDto);
        }

        return respXml;
    }

    private String buildTextMsg(AutoReplyRequestDto requestDto, String content) {
        return String.format(TEXT_XML_TEMPLATE, requestDto.getFromUserName(), requestDto.getToUserName(), System.currentTimeMillis(), content);
    }

    /**
     * 智能小胖回复
     *
     * @param requestDto
     * @return
     */
    private String getXiaoPangResp(AutoReplyRequestDto requestDto) {
        Repository repository = repositoryService.findAnswerByQuestion(requestDto.getContent());
        String result;

        if (repository == null) {
            result = buildTextMsg(requestDto, "我是一枚刚出生的婴儿，不会这个问题，你可以教我，下次我就会了, 有时候可能要多教几遍。\n\n教我步骤, 回复: \n“教你:问题描述==答案”\n\n例如回复：\n教你:你叫什么名字==小胖");
        } else {
            result = buildTextMsg(requestDto, repository.getAnswer());
        }

        return result;
    }

    private int getCategoryIndex(String content, int size) {
        for (int i = 1; i <= size; i++) {
            if (content.equals(String.valueOf(i))) {
                return i - 1;
            }
        }

        return -1;
    }

    /**
     * 回写响应
     *
     * @param response
     * @param respXml
     */
    public void writeResponse(HttpServletResponse response, String respXml) {
        // 写响应
        try {
            IOUtil.write(response.getOutputStream(), respXml);
        } catch (IOException e) {
            log.warn("写响应失败", e);
        }
    }

    private String getMenus() {
        StringBuilder sb = new StringBuilder();
        sb.append("文章栏目:\n");

        List<Category> categories = categoryService.findAllCategories();
        int sort = 0;
        for (Category category : categories) {
            sb.append(++sort).append(". <a href='https://kangyonggan.com/#category/").append(category.getCode()).append("'>").append(category.getName()).append("</a>\n");
        }

        sb.append("\n回复编号，享你所想！");
        return sb.toString();
    }
}
