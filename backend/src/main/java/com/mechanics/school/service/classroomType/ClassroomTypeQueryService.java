package com.mechanics.school.service.classroomType;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;

import java.util.List;
import java.util.Optional;

public interface ClassroomTypeQueryService {
    Optional<ClassroomTypeDto> FindClassroomTypeDtoById (Long id);
    List<ClassroomTypeDto> FindAllClassroomTypeDto();
}
