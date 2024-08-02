package com.mechanics.school.mapper.dtos.classroom;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateClassroomDto {
    @NotEmpty(message = "Name is required")
    String name;
    @Min(value = 1, message = "Capacity must be greater than 0")
    int capacity;
    @Min(value = 1, message = "ClassroomTypeId must be greater than 0")
    long classroomTypeId;
}
