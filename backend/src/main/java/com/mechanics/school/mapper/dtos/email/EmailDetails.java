package com.mechanics.school.mapper.dtos.email;

// Importing required classes

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// Class
public class EmailDetails {
    @NotEmpty(message = "recipient is required")
    String recipient;
    @NotEmpty(message = "body is required")
    String msgBody;
    String subject;
    String attachment;
}
