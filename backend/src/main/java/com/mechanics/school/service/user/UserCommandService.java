package com.mechanics.school.service.user;

import com.mechanics.school.mapper.dtos.user.UserCreateDto;
import com.mechanics.school.mapper.dtos.user.UserModifyDto;
import com.mechanics.school.model.User;

public interface UserCommandService {
     Boolean register(UserCreateDto userCreateDto);
     Boolean modify(UserModifyDto userModifyDto);
}
