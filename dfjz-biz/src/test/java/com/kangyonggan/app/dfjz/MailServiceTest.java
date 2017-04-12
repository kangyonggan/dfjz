package com.kangyonggan.app.dfjz;

import com.kangyonggan.app.dfjz.biz.service.MailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @since 4/12/17
 */
public class MailServiceTest extends AbstractServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSend() {
        mailService.send("kangyonggan@gmail.com", "测试邮件发送", "我就只是看看邮件能不能发送...");
    }

}
