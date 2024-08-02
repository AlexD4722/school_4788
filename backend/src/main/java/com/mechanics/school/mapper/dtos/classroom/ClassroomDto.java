package com.mechanics.school.mapper.dtos.classroom;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.model.Lesson;
import com.mechanics.school.utils.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassroomDto {
    long id;
    @NotEmpty(message = "Name is required")
    String name;
    @Min(value = 1, message = "Capacity must be greater than 0")
    int capacity;
    ClassroomTypeDto classroomTypeDto;
    Status status;
    List<Lesson> lessons;
}
