package com.mechanics.school.controller.student;

import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.student.StudentQueryService;
import com.mechanics.school.utils.LoggerUtils;
import com.mechanics.school.utils.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentQueryController {
    private final StudentQueryService studentQueryService;

    public StudentQueryController(StudentQueryService studentQueryService) {
        this.studentQueryService = studentQueryService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllStudent() {
        try {
            return ResponseHandler.responseBuilder(
                    "Student found",
                    HttpStatus.OK.value(),
                    studentQueryService.FindAll()
            );
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all StudentDto.", e);
            return ResponseHandler.responseBuilder(
                    "Error occurred during finding all StudentDto.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            );
        }
    }


}
