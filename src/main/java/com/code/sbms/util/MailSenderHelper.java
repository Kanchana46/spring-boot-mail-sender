package com.code.sbms.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class MailSenderHelper {


    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    public MailSenderHelper(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendMailWithAttachment(String to, String subject, String text, String attachmentPath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        helper.addAttachment("sample.pdf", new File(attachmentPath));
        mailSender.send(message);
    }

    public void sendHtmlMail(String to, String subject, String htmlFilePath) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        try (var inputStream = Objects.requireNonNull(MailSenderHelper.class.getResourceAsStream(htmlFilePath))) {
            helper.setText(
                    new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
                    true
            );
        }
        helper.setTo(to);
        helper.setSubject(subject);
        mailSender.send(message);
    }

}
