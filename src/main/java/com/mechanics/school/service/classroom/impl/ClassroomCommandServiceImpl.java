package com.mechanics.school.service.classroom.impl;

import com.mechanics.school.contracts.interfaces.Classroom.IClassroomRepository;
import com.mechanics.school.contracts.interfaces.ClassroomType.IClassroomTypeRepository;
import com.mechanics.school.mapper.ClassroomMapper;
import com.mechanics.school.mapper.dtos.classroom.CreateClassroomDto;
import com.mechanics.school.mapper.dtos.classroom.ModifyClassroomDto;
import com.mechanics.school.model.Classroom;
import com.mechanics.school.model.ClassroomType;
import com.mechanics.school.service.classroom.ClassroomCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.mechanics.school.utils.LoggerUtils.LOGGER;

@Service
public class ClassroomCommandServiceImpl implements ClassroomCommandService {
    private final IClassroomRepository classroomRepository;
    private final IClassroomTypeRepository classroomTypeRepository;
    private final ClassroomMapper classroomMapper;

    @Autowired
    public ClassroomCommandServiceImpl(IClassroomRepository classroomRepository, IClassroomTypeRepository classroomTypeRepository, ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.classroomTypeRepository = classroomTypeRepository;
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean insert(CreateClassroomDto createClassroomDto) {
        Optional<ClassroomType> classroomTypeOptional = classroomTypeRepository.findById(createClassroomDto.getClassroomTypeId());

        if (classroomTypeOptional.isEmpty()) {
            LOGGER.error("Classroom Type not found.");
            return false;
        }

        try {
            Classroom classroom = classroomMapper.CreateClassroomDtoToClassroom(createClassroomDto);
            classroom.setClassroomType(classroomTypeOptional.get());
            classroomRepository.save(classroom);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during insert.", e);
            return false;
        }
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean modify(ModifyClassroomDto modifyClassroomDto) {
        try {
            Classroom classroom = classroomRepository.findById(modifyClassroomDto.getId())
                    .orElseThrow(() -> {
                        LOGGER.error("Classroom not found.");
                        return new NoSuchElementException("Classroom not found.");
                    });

            ClassroomType classroomType = classroomTypeRepository.findById(modifyClassroomDto.getClassroomTypeId())
                    .orElseThrow(() -> {
                        LOGGER.error("Classroom Type not found.");
                        return new NoSuchElementException("Classroom Type not found.");
                    });

            classroomMapper.updateClassroomFromDto(modifyClassroomDto, classroom);
            classroom.setClassroomType(classroomType);
            classroomRepository.save(classroom);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during modification.", e);
            return false;
        }
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean delete(Long id) {
        try {
            if (!classroomRepository.existsById(id)) {
                LOGGER.error("Classroom not found.");
                return false;
            }
            classroomRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error occurred during deletion.", e);
            return false;
        }
    }
}

