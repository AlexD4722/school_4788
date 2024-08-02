package com.mechanics.school.mapper;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.mapper.dtos.classroomType.CreateClassroomTypeDto;
import com.mechanics.school.model.ClassroomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassroomTypeMapper {
    @Mapping(target = "createdAt", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    @Mapping(target = "lastModifiedDate", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    @Mapping(target = "status", expression = "java(com.mechanics.school.utils.enums.Status.ACTIVE)")
    ClassroomType CreateClassroomTypeDtoToClassroomType(CreateClassroomTypeDto createClassroomTypeDto);
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedDate", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    void updateClassroomTypeFromDto(ClassroomTypeDto classroomTypeDto, @MappingTarget ClassroomType classroomType);
}