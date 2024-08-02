package com.mechanics.school.service.user.impl;

import com.mechanics.school.contracts.interfaces.User.IUserRepository;
import com.mechanics.school.mapper.UserMapper;
import com.mechanics.school.mapper.dtos.user.UserCreateDto;
import com.mechanics.school.mapper.dtos.user.UserModifyDto;
import com.mechanics.school.model.User;
import com.mechanics.school.service.email.SendCodeVerifyToEmail;
import com.mechanics.school.service.user.UserCommandService;
import com.mechanics.school.utils.RandomCodeGenerator;
import com.mechanics.school.utils.jwt.PBKDF2Encoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.mechanics.school.utils.LoggerUtils.LOGGER;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PBKDF2Encoder passwordEncoder;
    private final SendCodeVerifyToEmail sendCodeVerifyToEmail;

    public UserCommandServiceImpl(IUserRepository userRepository, UserMapper userMapper, PBKDF2Encoder passwordEncoder, SendCodeVerifyToEmail sendCodeVerifyToEmail) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.sendCodeVerifyToEmail = sendCodeVerifyToEmail;
    }

    public Boolean isUserNameOrEmailExists(String userName, String email) {
        return userRepository.existsByUserNameOrEmail(userName, email);
    }

    @Override
    public Boolean register(UserCreateDto userCreateDto) {
        try {
            if (isUserNameOrEmailExists(userCreateDto.getUserName(), userCreateDto.getEmail())) {
                return false;
            }
            User user = new User();
            String encodedPassword = passwordEncoder.encode(userCreateDto.getPassword());
            userCreateDto.setPassword(encodedPassword);
            String codeVerify = RandomCodeGenerator.generateSixDigitCode();
            userMapper.UserCreateDtoToUser(userCreateDto, user);
            user.setVerifyCode(codeVerify);
            sendCodeVerifyToEmail.sendCodeToEmail(user.getEmail(), codeVerify);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during insert.", e);
            return false;
        }
    }

    @Override
    public Boolean modify(UserModifyDto userModifyDto) {
        try {
            User user = userRepository.findByCode(userModifyDto.getCode());
            if (user == null) {
                return false;
            }
            userMapper.UserModifyDtoToUser(userModifyDto, user);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during modify.", e);
            return false;
        }
    }



}
