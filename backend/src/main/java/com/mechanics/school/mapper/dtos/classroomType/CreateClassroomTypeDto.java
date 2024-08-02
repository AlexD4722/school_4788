package com.mechanics.school.mapper.dtos.classroomType;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateClassroomTypeDto {
    @NotEmpty(message = "Name is required")
    String name;
}