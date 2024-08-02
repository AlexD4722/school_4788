package com.mechanics.school.service.email;

public interface SendCodeVerifyToEmail {
    Boolean sendCodeToEmail(String email, String code);
}
