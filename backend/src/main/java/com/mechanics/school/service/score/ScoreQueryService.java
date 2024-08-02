package com.mechanics.school.service.score;

import com.mechanics.school.mapper.dtos.score.ScoreWithIdStudentDto;

import java.util.List;

public interface ScoreQueryService {
    List<ScoreWithIdStudentDto> FindByStudentCode(String code);
}
