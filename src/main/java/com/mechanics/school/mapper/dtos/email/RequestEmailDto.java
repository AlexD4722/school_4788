package com.mechanics.school.mapper.dtos.email;

import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.utils.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestEmailDto {
    @Nullable
    MultipartFile[] file;
    String to;
    String[] cc;
    String subject;
    String body;
}
