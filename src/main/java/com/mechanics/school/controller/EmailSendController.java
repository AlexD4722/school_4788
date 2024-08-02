package com.mechanics.school.controller;

// Importing required classes

import com.mechanics.school.mapper.dtos.email.EmailDetails;
import com.mechanics.school.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController
// Class
public class EmailSendController {
    private final EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Sending a simple Email
//    @PostMapping("/sendMail")
//    public String
//    sendMail(@Valid @RequestBody  EmailDetails details) {
//        String status = emailService.sendSimpleMail(details);
//        return status;
//    }
//
//    // Sending email with attachment
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(@Valid @RequestBody  EmailDetails details) {
//        String status = emailService.sendMailWithAttachment(details);
//        return status;
//    }
}