package com.xq.sendmail;

import com.xq.sendmail.config.MailProperties;
import com.xq.sendmail.utils.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class SendMailApplicationTests {

    @Autowired
    private MailProperties mailProperties;

    @Test
    public void sendMail() throws MessagingException {
        MailUtil.sendSimpleMail(mailProperties.getUsername(), "xxx@qq.com", "逗你玩", "祝你工作顺心");
    }

}
