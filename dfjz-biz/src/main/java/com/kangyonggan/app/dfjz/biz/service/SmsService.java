package com.kangyonggan.app.dfjz.biz.service;

/**
 * @author kangyonggan
 * @since 2017/2/9
 */
public interface SmsService {

    /**
     * 评论后发短信通知
     *
     * @param mobile
     * @param city
     * @param ip
     * @param title
     * @param content
     */
    void sendSms(String mobile, String city, String ip, String title, String content);

}
