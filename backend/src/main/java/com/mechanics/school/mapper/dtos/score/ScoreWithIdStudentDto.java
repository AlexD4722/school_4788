package com.mechanics.school.mapper.dtos.score;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScoreWithIdStudentDto {
    float score;
    String subjectName;
    String teacherName;
    String comment;
}
