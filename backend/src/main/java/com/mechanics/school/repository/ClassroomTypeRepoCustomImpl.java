package com.mechanics.school.repository;

import com.mechanics.school.contracts.interfaces.ClassroomType.IClassroomTypeRepositoryCustom;
import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.model.Classroom;
import com.mechanics.school.model.ClassroomType;
import com.mechanics.school.utils.enums.Status;
import com.mechanics.school.utils.LoggerUtils;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClassroomTypeRepoCustomImpl implements IClassroomTypeRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ClassroomTypeDto> FindById(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ClassroomTypeDto> query = cb.createQuery(ClassroomTypeDto.class);
            Root<ClassroomType> root = query.from(ClassroomType.class);
            query.multiselect(root.get("id"), root.get("name"), root.get("status"));
            query.where(cb.and(cb.equal(root.get("id"), id), cb.equal(root.get("status"), Status.ACTIVE)));
            return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());

        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding ClassroomTypeDto by id.", e);
            throw e;
        }
    }

    @Override
    public List<ClassroomTypeDto> FindAll() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<ClassroomType> root = query.from(ClassroomType.class);
            query.multiselect(
                    root.get("id").alias("id"),
                    root.get("name").alias("name"),
                    root.get("status").alias("status")
            );
            query.where(cb.equal(root.get("status"), Status.ACTIVE));
            query.distinct(true);
            List<Tuple> results = entityManager.createQuery(query).getResultList();
            //check if the result is empty
            if (results.isEmpty()) {
                return Collections.emptyList();
            }
            // Convert List<Tuple> to ClassroomTypeDto
            return results.stream()
                    .map(result -> {
                        ClassroomTypeDto dto = new ClassroomTypeDto();
                        dto.setId(result.get("id", Long.class));
                        dto.setName(result.get("name", String.class));
                        dto.setStatus(result.get("status", Status.class));
                        return dto;
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all ClassroomTypeDto.", e);
            throw e;
        }
    }

    @Override
    public Optional<ClassroomTypeDto> FindByIdWithClassroom(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<ClassroomType> root = query.from(ClassroomType.class);

            // Create a join with classrooms
            Join<ClassroomType, Classroom> classroomJoin = root.join("classrooms", JoinType.LEFT);
            query.multiselect(
                    root.get("id").alias("id"),
                    root.get("name").alias("name"),
                    root.get("status").alias("status"),
                    classroomJoin.get("id").alias("classroomId"),
                    classroomJoin.get("name").alias("classroomName"),
                    classroomJoin.get("capacity").alias("classroomCapacity"),
                    classroomJoin.get("status").alias("classroomStatus")
            );

            query.where(cb.and(
                    cb.equal(root.get("id"), id),
                    cb.equal(root.get("status"), Status.ACTIVE.getValue())
//                    cb.equal(classroomJoin.get("status"), Status.ACTIVE)
            ));
            query.distinct(true);

            List<Tuple> results = entityManager.createQuery(query).getResultList();
            //check if the result is empty
            if (results.isEmpty()) {
                return Optional.empty();
            }
            // Convert List<Tuple> to ClassroomTypeDto
            ClassroomTypeDto dto = new ClassroomTypeDto();
            dto.setId(results.getFirst().get("id", Long.class));
            dto.setName(results.getFirst().get("name", String.class));
            dto.setStatus(results.getFirst().get("status", Status.class));
            List<ClassroomDto> classroomDtos = results.stream()
                    .filter(result ->result.get("classroomId", Long.class) != null && result.get("classroomStatus", Status.class) == Status.ACTIVE)
                    .map(this::convertTupleToClassroomDto)
                    .collect(Collectors.toList());
            dto.setClassrooms(classroomDtos);
            // set other fields as necessary
            return Optional.ofNullable(dto);

        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding ClassroomTypeDto by id.", e);
            throw e;
        }
    }


    @Override
    public List<ClassroomTypeDto> FindAllWithClassroom() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<ClassroomType> root = query.from(ClassroomType.class);

            // Create a join with classrooms
            Join<ClassroomType, Classroom> classroomJoin = root.join("classrooms", JoinType.LEFT);

            // Include root in the select list
            query.multiselect(
                    root.get("id").alias("id"),
                    root.get("name").alias("name"),
                    root.get("status").alias("status"),
                    classroomJoin.get("id").alias("classroomId"),
                    classroomJoin.get("name").alias("classroomName"),
                    classroomJoin.get("capacity").alias("classroomCapacity"),
                    classroomJoin.get("status").alias("classroomStatus")
            );
            query.where(cb.and(cb.equal(root.get("status"), Status.ACTIVE.getValue())));
            query.distinct(true);

            List<Tuple> results = entityManager.createQuery(query).getResultList();
            //check if the result is empty
            if (results.isEmpty()) {
                return Collections.emptyList();
            }

            // Convert List<Tuple> to ClassroomTypeDto
            Map<Long, ClassroomTypeDto> classroomTypeDtoMap = new HashMap<>();
            for (Tuple result : results) {
                Long id = result.get("id", Long.class);
                ClassroomTypeDto dto = Optional.ofNullable(classroomTypeDtoMap.get(id)).orElseGet(() -> {
                    ClassroomTypeDto newDto = new ClassroomTypeDto();
                    newDto.setId(id);
                    newDto.setName(result.get("name", String.class));
                    newDto.setStatus(result.get("status", Status.class));
                    newDto.setClassrooms(new ArrayList<>());
                    classroomTypeDtoMap.put(id, newDto);
                    return newDto;
                });
                if (result.get("r", Long.class) != null && result.get("classroomStatus", Status.class) == Status.ACTIVE) {
                    dto.getClassrooms().add(convertTupleToClassroomDto(result));
                }
            }
            return new ArrayList<>(classroomTypeDtoMap.values());
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all ClassroomTypeDto.", e);
            throw new RuntimeException("Error occurred during finding all ClassroomTypeDto.", e);
        }
    }

    private ClassroomDto convertTupleToClassroomDto(Tuple result) {
        ClassroomDto classroomDto = new ClassroomDto();
        ;
        classroomDto.setId(result.get("classroomId", Long.class));
        classroomDto.setName(result.get("classroomName", String.class));
        classroomDto.setCapacity(result.get("classroomCapacity", Integer.class));
        classroomDto.setStatus(result.get("classroomStatus", Status.class));
        return classroomDto;
    }
}

