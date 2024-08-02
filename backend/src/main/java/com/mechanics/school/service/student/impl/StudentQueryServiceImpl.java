package com.mechanics.school.service.student.impl;

import com.mechanics.school.mapper.dtos.student.StudentDto;
import com.mechanics.school.repository.StudentRepoCustomImpl;
import com.mechanics.school.service.student.StudentQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentQueryServiceImpl implements StudentQueryService {
    private final StudentRepoCustomImpl studentRepoCustomImpl;
    public StudentQueryServiceImpl(StudentRepoCustomImpl studentRepoCustomImpl) {
        this.studentRepoCustomImpl = studentRepoCustomImpl;
    }
    @Override
    public List<StudentDto> FindAll() {
        return studentRepoCustomImpl.FindAll();
    }
    @Override
    public StudentDto FindByCode(String code) {
        return studentRepoCustomImpl.FindByCode(code);
    }
}
