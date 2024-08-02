package com.mechanics.school.mapper.dtos.user;

import com.mechanics.school.model.Guardian;
import com.mechanics.school.model.Student;
import com.mechanics.school.model.Teacher;
import com.mechanics.school.model.UserRole;
import com.mechanics.school.utils.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {
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
}
