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
     * @param articleid
     */
    void sendSms(String mobile, String articleid);

}
