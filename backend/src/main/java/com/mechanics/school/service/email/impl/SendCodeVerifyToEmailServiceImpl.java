package com.mechanics.school.service.email.impl;

import com.mechanics.school.mapper.dtos.email.EmailDetails;
import com.mechanics.school.service.email.EmailService;
import com.mechanics.school.service.email.SendCodeVerifyToEmail;
import org.springframework.stereotype.Service;

@Service
public class SendCodeVerifyToEmailServiceImpl implements SendCodeVerifyToEmail {
    private final EmailService emailService;

    public SendCodeVerifyToEmailServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public Boolean sendCodeToEmail(String email, String code) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject("Verification Code");
        emailDetails.setMsgBody("Your verification code is: " + code);
        return emailService.sendSimpleMail(emailDetails);
    }
}
