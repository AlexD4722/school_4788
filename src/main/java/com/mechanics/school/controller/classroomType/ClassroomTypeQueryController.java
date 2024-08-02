package com.mechanics.school.controller.classroomType;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.classroomType.ClassroomTypeQueryService;
import com.mechanics.school.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/classroomType")
public class ClassroomTypeQueryController {
    private  final ClassroomTypeQueryService classroomTypeQueryService;
    @Autowired
    public ClassroomTypeQueryController(ClassroomTypeQueryService classroomTypeQueryService) {
        this.classroomTypeQueryService = classroomTypeQueryService;
    }
    @GetMapping("")
    public ResponseEntity<Object> getAllClassroomType() {
        try {
            return ResponseHandler.responseBuilder(
                    "Classroom Type found",
                    HttpStatus.OK.value(),
                    classroomTypeQueryService.FindAllClassroomTypeDto()
            );
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all ClassroomTypeDto.", e);
            return ResponseHandler.responseBuilder(
                    "Something went wrong",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getClassroomType(@PathVariable Long id) {
        try {
            Optional<ClassroomTypeDto> classroomTypeDto = classroomTypeQueryService.FindClassroomTypeDtoById(id);
            if (classroomTypeDto.isEmpty()) {
                return ResponseHandler.responseBuilder(
                        "Classroom Type not found",
                        HttpStatus.NOT_FOUND.value(),
                        null
                );
            }
            return ResponseHandler.responseBuilder(
                    "Classroom Type found",
                    HttpStatus.OK.value(),
                    classroomTypeDto
            );
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding ClassroomTypeDto by id.", e);
            return ResponseHandler.responseBuilder(
                    "Error occurred during finding ClassroomTypeDto by id.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            );
        }
    }

}
