package com.mechanics.school.mapper.dtos.student;

import com.mechanics.school.utils.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
    public class StudentDto {
    String code;
    String firstName;
    String lastName;
    Date dataOfBirth;
    Date enrollmentDate;
    Gender gender = Gender.MALE;
    String image;
    String address;
}
