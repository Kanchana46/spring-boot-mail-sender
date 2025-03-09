package com.code.sbms.service;

import com.code.sbms.util.MailSenderHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${mail.to}")
    private String to;

    private final MailSenderHelper helper;

    public MailService(MailSenderHelper helper) {
        this.helper = helper;
    }

    public String sendMail() {
        try {
            helper.sendSimpleMail(to, "Email Notification", "Hello from Spring Boot");
            return "Email sent successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String sendAttachmentMail() {
        try {
            helper.sendMailWithAttachment(to, "Email Notification", "Hello from Spring Boot","sample.pdf");
            return "Email sent successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String sendHtmlMail() {
        try {
            helper.sendHtmlMail(to, "Email Notification", "/templates/mailTemplate.html");
            return "Email sent successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
