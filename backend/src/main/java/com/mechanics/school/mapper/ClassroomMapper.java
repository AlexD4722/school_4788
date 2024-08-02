package com.mechanics.school.mapper;

import com.mechanics.school.mapper.dtos.classroom.CreateClassroomDto;
import com.mechanics.school.mapper.dtos.classroom.ModifyClassroomDto;
import com.mechanics.school.model.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    @Mapping(target = "createdAt", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    @Mapping(target = "lastModifiedDate", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    @Mapping(target = "status", expression = "java(com.mechanics.school.utils.enums.Status.ACTIVE)")
    Classroom CreateClassroomDtoToClassroom(CreateClassroomDto createClassroomDto);
    @Mapping(target = "lastModifiedDate", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    void updateClassroomFromDto(ModifyClassroomDto modifyClassroomDto, @MappingTarget Classroom classroom);
}
