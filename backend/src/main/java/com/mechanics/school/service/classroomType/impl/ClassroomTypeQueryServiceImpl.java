package com.mechanics.school.service.classroomType.impl;

import com.mechanics.school.contracts.interfaces.ClassroomType.IClassroomTypeRepository;
import com.mechanics.school.contracts.interfaces.ClassroomType.IClassroomTypeRepositoryCustom;
import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.service.classroomType.ClassroomTypeQueryService;
import com.mechanics.school.utils.LoggerUtils;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.mechanics.school.utils.LoggerUtils.LOGGER;

@Service
public class ClassroomTypeQueryServiceImpl implements ClassroomTypeQueryService {
    private final IClassroomTypeRepository classroomTypeRepository;
    private final IClassroomTypeRepositoryCustom classroomTypeRepositoryCustom;

    @Autowired
    public ClassroomTypeQueryServiceImpl(IClassroomTypeRepository classroomTypeRepository, IClassroomTypeRepositoryCustom classroomTypeRepositoryCustom) {
        this.classroomTypeRepository = classroomTypeRepository;
        this.classroomTypeRepositoryCustom = classroomTypeRepositoryCustom;
    }

    @Override
    public Optional<ClassroomTypeDto> FindClassroomTypeDtoById(Long id) {
        try {
            return classroomTypeRepositoryCustom.FindByIdWithClassroom(id);
        } catch (NonUniqueResultException e) {
            LoggerUtils.LOGGER.error("Non unique result found", e);
            return Optional.empty();
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding Classroom by id.", e);
            return Optional.empty();
        }
    }

    @Override
    public List<ClassroomTypeDto> FindAllClassroomTypeDto() {
        try {
            return classroomTypeRepositoryCustom.FindAllWithClassroom();
        } catch (Exception e) {
            LOGGER.error("Error occurred during finding all ClassroomTypeDto.", e);
            return Collections.emptyList();
        }
    }
}
