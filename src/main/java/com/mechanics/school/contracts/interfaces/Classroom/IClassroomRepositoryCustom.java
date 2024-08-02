package com.mechanics.school.contracts.interfaces.Classroom;

import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;

import java.util.List;

public interface IClassroomRepositoryCustom {
    ClassroomDto findById (Long id);
    List<ClassroomDto> findAll();
}
