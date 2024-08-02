package com.mechanics.school.mapper.dtos.classroomType;

import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.utils.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassroomTypeDto {
    long id;
    @NotEmpty(message = "Name is required")
    String name;
    Status status;
    List<ClassroomDto> classrooms;
}