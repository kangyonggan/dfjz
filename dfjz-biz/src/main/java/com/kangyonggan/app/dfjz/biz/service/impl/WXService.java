package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.common.IOUtil;
import com.kangyonggan.app.dfjz.common.XmlUtil;
import com.kangyonggan.app.dfjz.model.dto.AutoReplyRequestDto;
import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kangyonggan
 * @since 7/28/17
 */
@Service
@Log4j2
public class WXService {

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
            "</item>\n"+
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
            String content = root.element("Content").getTextTrim();// 文本消息内容
            String msgId = root.element("MsgId").getTextTrim();// 消息id，64位整型

            requestDto.setMsgId(msgId);
            requestDto.setToUserName(toUserName);
            requestDto.setMsgType(msgType);
            requestDto.setFromUserName(fromUserName);
            requestDto.setCreateTime(createTime);
            requestDto.setContent(content);
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
    public String getResponseXml(AutoReplyRequestDto requestDto) {
        String content = requestDto.getContent();
        String respXml;

        if (content.equals("1")) {
            respXml = String.format(NEWS_XML_TEMPLATE, requestDto.getFromUserName(), requestDto.getToUserName(), System.currentTimeMillis(), "身份证查询", "输入身份证号码可以查询你省份、地区、性别、生肖等", "https://kangyonggan.com/static/app/images/tools/idcard.png", "https://kangyonggan.com/#tools/idcard");
        } else {
            respXml = String.format(TEXT_XML_TEMPLATE, requestDto.getFromUserName(), requestDto.getToUserName(), System.currentTimeMillis(), getMenus());
        }

        return respXml;
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
        sb.append("自助菜单:\n\n");
        sb.append("1. <a href='https://kangyonggan.com/#tools/idcard'>身份证查询</a>\n");
        sb.append("2. 生成八字推算\n");
        sb.append("3. 二维码生成\n");
        sb.append("4. 逆天邪神\n");
        sb.append("5. Java后台\n");
        sb.append("6. Web前端\n");
        sb.append("7. 系统运维\n");
        sb.append("8. 代码片段\n");
        sb.append("9. 综合\n\n");
        sb.append("回复编号，即可打开内容！");
        return sb.toString();
    }
}
