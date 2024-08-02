package com.mechanics.school.service.user;

import com.mechanics.school.mapper.dtos.auth.AuthUserDto;
import com.mechanics.school.model.User;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


public interface UserQueryService {
    @Transactional
    Mono<User> findUserByUser_name(String user_name);
    Mono<AuthUserDto> findAuthUserDtoByUser_name(String user_name);
}
