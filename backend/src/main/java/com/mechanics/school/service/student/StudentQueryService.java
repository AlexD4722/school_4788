package com.mechanics.school.service.student;

import com.mechanics.school.mapper.dtos.student.StudentDto;

import java.util.List;

public interface StudentQueryService {
    List<StudentDto> FindAll();
    StudentDto FindByCode(String code);
}
