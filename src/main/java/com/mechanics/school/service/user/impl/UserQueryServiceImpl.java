package com.mechanics.school.service.user.impl;

import com.mechanics.school.contracts.interfaces.User.IUserRepository;
import com.mechanics.school.contracts.interfaces.User.IUserRepositoryCustom;
import com.mechanics.school.mapper.dtos.auth.AuthUserDto;
import com.mechanics.school.model.User;
import com.mechanics.school.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final IUserRepository userRepository;
    private final IUserRepositoryCustom userRepositoryCustom;

    @Autowired
    public UserQueryServiceImpl(IUserRepository userRepository, IUserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    @Override
    public Mono<User> findUserByUser_name(String user_name) {
        User user = userRepository.findByUserName(user_name);
        return Mono.justOrEmpty(user);
    }

    @Override
    public Mono<AuthUserDto> findAuthUserDtoByUser_name(String user_name) {
        return Mono.justOrEmpty(userRepositoryCustom.findUserByUser_name(user_name));
    }

}
