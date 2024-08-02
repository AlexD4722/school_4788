package com.mechanics.school.service.classroom;

import com.mechanics.school.mapper.dtos.classroom.CreateClassroomDto;
import com.mechanics.school.mapper.dtos.classroom.ModifyClassroomDto;

public interface ClassroomCommandService {
    boolean insert  (CreateClassroomDto createClassroomDto);
    boolean modify (ModifyClassroomDto modifyClassroomDto);
    boolean delete (Long id);
}
