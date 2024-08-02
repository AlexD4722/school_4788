package com.mechanics.school.mapper;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.mapper.dtos.classroomType.CreateClassroomTypeDto;
import com.mechanics.school.mapper.dtos.user.UserCreateDto;
import com.mechanics.school.mapper.dtos.user.UserModifyDto;
import com.mechanics.school.model.ClassroomType;
import com.mechanics.school.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    @Mapping(target = "lastModifiedDate", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    @Mapping(target = "status", expression = "java(com.mechanics.school.utils.enums.Status.INACTIVE)")
    @Mapping(target = "code", expression = "java(com.mechanics.school.utils.RandomCodeGenerator.generateRandomCode())")
    void UserCreateDtoToUser(UserCreateDto createUserDto, @MappingTarget User user);

    @Mapping(target = "lastModifiedDate", expression = "java(com.mechanics.school.utils.DateUtils.getTime())")
    void UserModifyDtoToUser(UserModifyDto modifyUserDto, @MappingTarget User user);
}
