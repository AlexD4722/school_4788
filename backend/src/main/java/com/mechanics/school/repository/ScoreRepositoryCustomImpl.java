package com.mechanics.school.repository;

import com.mechanics.school.contracts.interfaces.score.IScoreRepositoryCustom;
import com.mechanics.school.mapper.dtos.score.ScoreWithIdStudentDto;
import com.mechanics.school.model.*;
import com.mechanics.school.utils.LoggerUtils;
import com.mechanics.school.utils.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreRepositoryCustomImpl  implements IScoreRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ScoreWithIdStudentDto> FindByStudentCode(String code) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<Scores> root = query.from(Scores.class);

            Join <Scores, Subject> subjectJoin = root.join("subject", JoinType.LEFT);
            Join<Scores, Teacher> teacherJoin = root.join("teacher", JoinType.LEFT);

            query.multiselect(
                    root.get("score").alias("score"),
                    subjectJoin.get("name").alias("subjectName"),
                    teacherJoin.get("name").alias("teacherName"),
                    root.get("comment").alias("comment")
            );

            query.where(cb.and(cb.equal(root.get("student").get("code"), code), cb.equal(root.get("status"), Status.ACTIVE.getValue())));
            query.distinct(true); // to avoid duplicate results

            List<Tuple> results = entityManager.createQuery(query).getResultList();
            //check if the result is empty
            if (results.isEmpty()) {
                return List.of();
            }
            // Convert List<Tuple> to ScoreWithIdStudentDto
            return results.stream()
                    .map(result -> new ScoreWithIdStudentDto(
                            result.get("score", Float.class),
                            result.get("subjectName", String.class),
                            result.get("teacherName", String.class),
                            result.get("comment", String.class)
                    ))
                    .toList();

        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding ClassroomTypeDto by id.", e);
            return List.of();
        }
    }
}
