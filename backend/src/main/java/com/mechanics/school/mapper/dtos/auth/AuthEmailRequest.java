package com.mechanics.school.mapper.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthEmailRequest {
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;
    @NotNull(message = "Code is required")
    String code;
}
