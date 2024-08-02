package com.mechanics.school.contracts.interfaces.score;

import com.mechanics.school.model.Scores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScoreRepository extends JpaRepository<Scores, Long> {
}
