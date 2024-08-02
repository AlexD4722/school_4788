package com.mechanics.school.service.score.impl;

import com.mechanics.school.mapper.dtos.score.ScoreWithIdStudentDto;
import com.mechanics.school.repository.ScoreRepositoryCustomImpl;
import com.mechanics.school.service.score.ScoreQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreQueryServiceImpl implements ScoreQueryService {
    private final ScoreRepositoryCustomImpl scoreRepositoryCustom;

    public ScoreQueryServiceImpl(ScoreRepositoryCustomImpl scoreRepositoryCustom) {
        this.scoreRepositoryCustom = scoreRepositoryCustom;
    }

    @Override
    public List<ScoreWithIdStudentDto> FindByStudentCode(String code) {
        return scoreRepositoryCustom.FindByStudentCode(code);
    }
}
