package com.mechanics.school.service.classroomType.impl;

import com.mechanics.school.contracts.interfaces.ClassroomType.IClassroomTypeRepository;
import com.mechanics.school.mapper.ClassroomTypeMapper;
import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.mapper.dtos.classroomType.CreateClassroomTypeDto;
import com.mechanics.school.model.ClassroomType;
import com.mechanics.school.service.classroomType.ClassroomTypeCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mechanics.school.utils.LoggerUtils.LOGGER;

@Service
public class ClassroomTypeCommandServiceImpl implements ClassroomTypeCommandService {
    private final IClassroomTypeRepository classroomTypeRepository;
    private final ClassroomTypeMapper classroomTypeMapper;

    @Autowired
    public ClassroomTypeCommandServiceImpl(IClassroomTypeRepository classroomTypeRepository, ClassroomTypeMapper classroomTypeMapper) {
        this.classroomTypeRepository = classroomTypeRepository;
        this.classroomTypeMapper = classroomTypeMapper;
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean insert(CreateClassroomTypeDto createClassroomTypeDto) {
        try {
            ClassroomType classroomType = classroomTypeMapper.CreateClassroomTypeDtoToClassroomType(createClassroomTypeDto);
            classroomTypeRepository.save(classroomType);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during insert.", e);
            return false;
        }
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean modify(ClassroomTypeDto classroomTypeDto) {
        try {
            ClassroomType classroomType = classroomTypeRepository.findById(classroomTypeDto.getId()).orElse(null);
            if (classroomType == null) {
                return false;
            }
            classroomTypeMapper.updateClassroomTypeFromDto(classroomTypeDto, classroomType);
            classroomTypeRepository.save(classroomType);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during modify.", e);
            return false;
        }
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean delete(Long id) {
        try {
            ClassroomType classroomType = classroomTypeRepository.findById(id).orElse(null);
            if (classroomType == null) {
                return false;
            }
            classroomTypeRepository.delete(classroomType);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during delete.", e);
            return false;
        }
    }
}
