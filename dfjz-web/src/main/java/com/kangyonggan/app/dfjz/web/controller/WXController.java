package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.impl.WXService;
import com.kangyonggan.app.dfjz.model.dto.AutoReplyRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangyonggan
 * @since 7/28/17
 */
@RestController
@RequestMapping("wx")
@Log4j2
public class WXController {

    @Autowired
    private WXService wxService;

    /**
     * 验证开发者服务器
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        log.info("========= 微信订阅号后台收到一个请求 =========");

        AutoReplyRequestDto requestDto = wxService.getRequestDtoFromRequest(request);

        String respXml = wxService.getResponseXml(requestDto.getFromUserName(), requestDto.getToUserName(), requestDto.getContent());
        log.info("响应报文：{}", respXml);

        wxService.writeResponse(response, respXml);
        return "success";
    }

}
