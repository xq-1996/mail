package com.xq.sendmail.utils;


import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * 邮件发送
 */
@NoArgsConstructor
public class MailUtil {

    private static Logger log = LoggerFactory.getLogger(MailUtil.class);

    private static final JavaMailSender MAIL_SENDER;


    static {
        MAIL_SENDER = SpringBeanUtil.getBean(JavaMailSender.class);
    }


    /**
     * 发送简单文本邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    public static void sendSimpleMail(String from, String to, String subject, String content) throws MessagingException {
        checkParams(from, to, subject, content);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        MAIL_SENDER.send(message);
    }


    /**
     * 发送html 邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    public static void sendHTMLMail(String from, String to, String subject, String content) throws MessagingException {
        checkParams(from, to, subject, content);
        MimeMessage message = MAIL_SENDER.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setText(content, true);
        helper.setSubject(subject);
        MAIL_SENDER.send(message);
    }

    private static void checkParams(String... obj) {
        for (String content : obj) {
            if (Objects.isNull(content)) {
                log.error("【邮件发送异常】缺少参数, obj:{}", (Object) obj);
                throw new RuntimeException("【邮件发送】缺少参数!");
            }
        }
    }


}
