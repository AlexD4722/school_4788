package com.mechanics.school.service.classroomType;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.mapper.dtos.classroomType.CreateClassroomTypeDto;

public interface ClassroomTypeCommandService {
    boolean insert(CreateClassroomTypeDto createClassroomTypeDto);
    boolean modify(ClassroomTypeDto classroomType);
    boolean delete(Long id);
}
