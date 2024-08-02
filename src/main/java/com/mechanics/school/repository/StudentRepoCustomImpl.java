package com.mechanics.school.repository;

import com.mechanics.school.contracts.interfaces.Student.IStudentRepositoryCustom;
import com.mechanics.school.mapper.dtos.student.StudentDto;
import com.mechanics.school.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StudentRepoCustomImpl  implements IStudentRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<StudentDto> FindAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentDto> query = cb.createQuery(StudentDto.class);
        Root<Student> root = query.from(Student.class);

        query.multiselect(
                root.get("code").alias("code"),
                root.get("firstName").alias("surname"),
                root.get("lastName").alias("lastName"),
                root.get("dataOfBirth").alias("email"),
                root.get("enrollmentDate").alias("phone"),
                root.get("gender").alias("status"),
                root.get("image").alias("image"),
                root.get("address").alias("address")
        );
        return entityManager.createQuery(query).getResultList();
    }
}
