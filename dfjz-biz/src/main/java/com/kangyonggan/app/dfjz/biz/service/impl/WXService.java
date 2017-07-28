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

    private static String RESP_XML_TEMPLATE = "<xml>" +
            "<ToUserName><![CDATA[%s]]></ToUserName>" +
            "<FromUserName><![CDATA[%s]]></FromUserName>" +
            "<CreateTime>%d</CreateTime>" +
            "<MsgType><![CDATA[text]]></MsgType>" +
            "<Content><![CDATA[%s]]></Content>" +
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
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public String getResponseXml(String toUserName, String fromUserName, String content) {
        return String.format(RESP_XML_TEMPLATE, toUserName, fromUserName, System.currentTimeMillis(), content);
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

}
