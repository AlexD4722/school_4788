package com.mechanics.school.repository;

import com.mechanics.school.contracts.interfaces.Classroom.IClassroomRepositoryCustom;
import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.model.Classroom;
import com.mechanics.school.model.ClassroomType;
import com.mechanics.school.model.Lesson;
import com.mechanics.school.utils.enums.Status;
import com.mechanics.school.utils.LoggerUtils;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class ClassroomRepoCustomImpl implements IClassroomRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClassroomDto findById(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<Classroom> root = query.from(Classroom.class);

            // Use join instead of fetch
            Join<Classroom, ClassroomType> classroomTypeJoin = root.join("classroomType", JoinType.LEFT);

            query.multiselect(
                    root.get("id").alias("id"),
                    root.get("name").alias("name"),
                    root.get("capacity").alias("capacity"),
                    root.get("status").alias("status"),
                    classroomTypeJoin.get("id").alias("classroomTypeId"),
                    classroomTypeJoin.get("name").alias("classroomTypeName")
            );
            query.where(cb.and(cb.equal(root.get("id"), id), cb.equal(root.get("status"), Status.ACTIVE.getValue())));
            query.distinct(true); // to avoid duplicate results

            Tuple result = entityManager.createQuery(query).getSingleResult();
            return new ClassroomDto(
                    result.get("id", Long.class),
                    result.get("name", String.class),
                    result.get("capacity", Integer.class),
                    convertClassroomTypeDto(result),
                    result.get("status", Status.class),
                    null
            );
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during find by id.", e);
            return null;
        }
    }

    @Override
    public List<ClassroomDto> findAll() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<Classroom> root = query.from(Classroom.class);

            // Perform fetch join with ClassroomType
            Join<Classroom, ClassroomType> classroomTypeJoin = root.join("classroomType", JoinType.LEFT);
            Join<Classroom, Lesson> lessonJoin = root.join("lessons", JoinType.LEFT);
            // Include root in the select list

            query.multiselect(
                    root.get("id").alias("id"),
                    root.get("name").alias("name"),
                    root.get("capacity").alias("capacity"),
                    root.get("status").alias("status"),
                    classroomTypeJoin.get("id").alias("classroomTypeId"),
                    classroomTypeJoin.get("name").alias("classroomTypeName"),
                    classroomTypeJoin.get("status").alias("classroomTypeStatus")
//                    lessonJoin.get("id").alias("lessonId"),
//                    lessonJoin.get("subject").alias("lessonSubject"),
//                    lessonJoin.get("status").alias("lessonStatus")
            );
            query.where(cb.equal(root.get("status"), Status.ACTIVE.getValue()));
            query.distinct(true);

            List<Tuple> results = entityManager.createQuery(query).getResultList();
            return results.stream()
//                    .filter(result -> result.get("classroomTypeStatus", Status.class) == Status.ACTIVE)
                    .map(result -> new ClassroomDto(
                    result.get("id", Long.class),
                    result.get("name", String.class),
                    result.get("capacity", Integer.class),
                    new ClassroomTypeDto(
                            result.get("classroomTypeId", Long.class),
                            result.get("classroomTypeName", String.class),
                            result.get("classroomTypeStatus", Status.class),
                            null
                    ),
                    result.get("status", Status.class),
                    null
            )).collect(Collectors.toList());
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all ClassroomTypeDto.", e);
            return List.of();
        }
    }
    private ClassroomTypeDto convertClassroomTypeDto(Tuple result) {
        ClassroomTypeDto classroomTypeDto = new ClassroomTypeDto();
        classroomTypeDto.setId(result.get("classroomTypeId", Long.class));
        classroomTypeDto.setName(result.get("classroomTypeName", String.class));
        return classroomTypeDto;
    }
}
