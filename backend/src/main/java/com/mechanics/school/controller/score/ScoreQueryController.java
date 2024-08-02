package com.mechanics.school.controller.score;

import com.mechanics.school.mapper.dtos.score.ScoreWithIdStudentDto;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.score.ScoreQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
public class ScoreQueryController {
    private final ScoreQueryService scoreQueryService;

    public ScoreQueryController(ScoreQueryService scoreQueryService) {
        this.scoreQueryService = scoreQueryService;
    }

    // Find score by student code
    @GetMapping("/score/{code}")
    public ResponseEntity<Object> FindByStudentCode(@PathVariable String code) {
        List<ScoreWithIdStudentDto> scoreWithIdStudentDto = scoreQueryService.FindByStudentCode(code);
        if (scoreWithIdStudentDto != null && !scoreWithIdStudentDto.isEmpty()) {
            return ResponseHandler.responseBuilder(
                    "Score found successfully",
                    HttpStatus.OK.value(),
                    scoreWithIdStudentDto
            );
        }
        return ResponseHandler.responseBuilder(
                "No scores found for the given student code",
                HttpStatus.NOT_FOUND.value(),
                null
        );
    }
}