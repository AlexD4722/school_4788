package com.mechanics.school.service.classroom.impl;

import com.mechanics.school.contracts.interfaces.Classroom.IClassroomRepository;
import com.mechanics.school.contracts.interfaces.Classroom.IClassroomRepositoryCustom;
import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.model.Classroom;
import com.mechanics.school.service.classroom.ClassroomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    ClassroomQueryServiceImpl  implements ClassroomQueryService {
    private  final IClassroomRepository classroomRepository;
    private  final IClassroomRepositoryCustom classroomRepositoryCustom;

    @Autowired
    public ClassroomQueryServiceImpl(IClassroomRepository classroomRepository, IClassroomRepositoryCustom classroomRepositoryCustom) {
        this.classroomRepository = classroomRepository;
        this.classroomRepositoryCustom = classroomRepositoryCustom;
    }

    @Override
    public ClassroomDto FindClassroomDtoById(Long id) {
        return this.classroomRepositoryCustom.findById(id);
    }

    @Override
    public List<ClassroomDto> FindAllClassroomDto() {
        return this.classroomRepositoryCustom.findAll();
    }

    @Override
    public List<Classroom> searchClassroomDto(Long id, String name) {
        return classroomRepository.searchClassroomType(id, name);
    }
}
