package com.mechanics.school.mapper.dtos.classroom;

import com.mechanics.school.utils.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModifyClassroomDto {
    @NotNull(message = "Name is required")
    long id;
    @NotEmpty(message = "Name is required")
    String name;
    @Min(value = 1, message = "Capacity must be greater than 0")
    int capacity;
    Long classroomTypeId;
    Status status;
}
