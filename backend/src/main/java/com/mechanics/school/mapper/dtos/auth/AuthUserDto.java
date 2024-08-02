package com.mechanics.school.mapper.dtos.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserDto {
    String userName;
    String password;
    List<String> roles;
}
