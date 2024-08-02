package com.mechanics.school.contracts.interfaces.Student;

import com.mechanics.school.mapper.dtos.student.StudentDto;

import java.util.List;

public interface IStudentRepositoryCustom {
    List<StudentDto> FindAll();
    StudentDto FindByCode(String code);
}
