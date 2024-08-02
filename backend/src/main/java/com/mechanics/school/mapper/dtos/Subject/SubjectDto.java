package com.mechanics.school.mapper.dtos.Subject;

import com.mechanics.school.model.Department;
import com.mechanics.school.model.Lesson;
import com.mechanics.school.model.Scores;
import com.mechanics.school.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectDto {
    String name;
//    Department department;
}
