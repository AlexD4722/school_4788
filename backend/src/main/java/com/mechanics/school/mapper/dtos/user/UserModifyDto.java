package com.mechanics.school.mapper.dtos.user;

import com.mechanics.school.utils.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyDto {
    @NotNull(message = "Code is required")
    String code;
    @NotNull(message = "User name is required")
    String userName;
    @NotNull(message = "Password is required")
    String password;
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;
    @NotNull(message = "Phone is required")
    String phone;
    @NotNull(message = "Address is required")
    String address;
    @NotNull(message = "Status is required")
    Status status;
}
