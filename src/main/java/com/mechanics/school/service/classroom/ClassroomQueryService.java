package com.mechanics.school.service.classroom;

import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.model.Classroom;

import java.util.List;

public interface ClassroomQueryService {
    ClassroomDto FindClassroomDtoById (Long id);
    List<ClassroomDto> FindAllClassroomDto();
    List<Classroom> searchClassroomDto(Long id, String name);
}
