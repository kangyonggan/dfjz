package com.kangyonggan.app.dfjz.biz.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.kangyonggan.app.dfjz.biz.service.SmsService;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @author kangyonggan
 * @since 2017/2/9
 */
@Log4j2
public class SmsServiceImpl implements SmsService {

    @Setter
    private String signName;

    @Setter
    private String templateCode;

    @Setter
    private String debug;

    private IAcsClient client;

    public SmsServiceImpl(String regionId, String accessKeyId, String secret, String domain) {
        try {
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
            DefaultProfile.addEndpoint(regionId, regionId, "Sms", domain);
            client = new DefaultAcsClient(profile);
        } catch (Exception e) {
            log.error("初始化短信配置失败", e);
        }
    }

    @Override
    @LogTime
    public void sendSms(String mobile, String city, String ip, String title, String content) {
        SingleSendSmsRequest request = new SingleSendSmsRequest();
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setRecNum(mobile);
        request.setParamString("{\"city\":\"" + city + "\", \"ip\":\"" + ip + "\", \"title\":\"" + title + "\", \"content\":\"" + content + "\"}");

        // 发送
        try {
            SingleSendSmsResponse response;
            if (!"true".equals(debug)) {
                response = client.getAcsResponse(request);
            } else {
                log.info("调试阶段不真正的发短信");
                response = new SingleSendSmsResponse();
                response.setRequestId("BFDBE6CE-C2C5-46C0-8EC6-8A6628AAF53F");
                response.setModel("105862070614^1107954709798");
            }
            log.info("请求ID:{}", response.getRequestId());
            log.info("响应结果:{}", response.getModel());
            log.info("短信发送成功");
        } catch (Exception e) {
            log.error("评论后的短信通知发送失败", e);
        }
    }
}
