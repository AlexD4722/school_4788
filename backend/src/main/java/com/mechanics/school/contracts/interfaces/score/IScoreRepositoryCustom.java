package com.mechanics.school.contracts.interfaces.score;


import com.mechanics.school.mapper.dtos.score.ScoreWithIdStudentDto;

import java.util.List;

public interface IScoreRepositoryCustom {
    List<ScoreWithIdStudentDto> FindByStudentCode(String code);
}
