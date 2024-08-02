package com.mechanics.school.service.email;

import com.mechanics.school.mapper.dtos.email.EmailDetails;

public interface EmailService {

    Boolean sendSimpleMail(EmailDetails details);

    Boolean sendMailWithAttachment(EmailDetails details);
}