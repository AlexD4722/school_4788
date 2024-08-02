package com.mechanics.school.contracts.interfaces.User;

import com.mechanics.school.mapper.dtos.auth.AuthUserDto;

public interface IUserRepositoryCustom {
    AuthUserDto findUserByUser_name(String user_name);
}
