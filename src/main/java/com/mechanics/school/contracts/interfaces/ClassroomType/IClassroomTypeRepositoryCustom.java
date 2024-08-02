package com.mechanics.school.contracts.interfaces.ClassroomType;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;

import java.util.List;
import java.util.Optional;

public interface IClassroomTypeRepositoryCustom
{
    Optional<ClassroomTypeDto> FindById (Long id);
    Optional<ClassroomTypeDto> FindByIdWithClassroom(Long id);
    List<ClassroomTypeDto> FindAll();
    List<ClassroomTypeDto> FindAllWithClassroom();
}
