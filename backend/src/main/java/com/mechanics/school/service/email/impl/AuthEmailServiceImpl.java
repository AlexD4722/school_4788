package com.mechanics.school.service.email.impl;

import com.mechanics.school.contracts.interfaces.User.IUserRepository;
import com.mechanics.school.model.User;
import com.mechanics.school.service.email.AuthEmailService;
import com.mechanics.school.utils.enums.Status;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthEmailServiceImpl implements AuthEmailService {
    private final IUserRepository userRepository;

    public AuthEmailServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean authEmail(String email, String code) {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return false;
            }
            if (user.getVerifyCode().equals(code)) {
                user.setVerifyCode(null);
                user.setStatus(Status.ACTIVE);
                userRepository.save(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(AuthEmailServiceImpl.class.getName()).severe(e.getMessage());
            return false;
        }
    }
}
