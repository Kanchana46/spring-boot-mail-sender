package com.code.sbms.controller;

import com.code.sbms.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    public MailController (MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/send")
    public String sendMail() {
        return mailService.sendMail();
    }

    @GetMapping("/sendWithAttachment")
    public String sendAttachmentMail() {
        return mailService.sendAttachmentMail();
    }

    @GetMapping("/sendHtml")
    public String sendHtmlMail() {
        return mailService.sendHtmlMail();
    }
}
