package com.mechanics.school.controller.classroomType;

import com.mechanics.school.mapper.dtos.classroomType.ClassroomTypeDto;
import com.mechanics.school.mapper.dtos.classroomType.CreateClassroomTypeDto;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.classroomType.ClassroomTypeCommandService;
import com.mechanics.school.utils.LoggerUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classroomType")
public class ClassroomTypeCommandController {
    private final ClassroomTypeCommandService classroomTypeCommandService;

    @Autowired
    public ClassroomTypeCommandController(ClassroomTypeCommandService classroomTypeCommandService) {
        this.classroomTypeCommandService = classroomTypeCommandService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createClassroomType(@Valid @RequestBody CreateClassroomTypeDto createClassroomTypeDto) {

        if (classroomTypeCommandService.insert(createClassroomTypeDto)) {
            LoggerUtils.LOGGER.info("Classroom Type Created Successfully");
            return ResponseHandler.responseBuilder(
                    "Classroom Type Created Successfully",
                    HttpStatus.CREATED.value(),
                    createClassroomTypeDto);
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during creating ClassroomTypeDto.",
                HttpStatus.BAD_REQUEST.value(), null);
    }

    @PutMapping("")
    public ResponseEntity<Object> updateClassroomType(@Valid @RequestBody ClassroomTypeDto classroomTypeDto) {
        if (classroomTypeCommandService.modify(classroomTypeDto)) {
            LoggerUtils.LOGGER.info("Classroom Type Updated Successfully");
            return ResponseHandler.responseBuilder(
                    "Classroom Type Updated Successfully",
                    HttpStatus.OK.value(),
                    classroomTypeDto);
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during updating ClassroomTypeDto.",
                HttpStatus.CONFLICT.value(), null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClassroomType(@PathVariable Long id) {
        if (classroomTypeCommandService.delete(id)) {
            LoggerUtils.LOGGER.info("Classroom Type Deleted Successfully");
            return ResponseHandler.responseBuilder(
                    "Classroom Type Deleted Successfully",
                    HttpStatus.OK.value(),
                    id);
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during deleting ClassroomTypeDto.",
                HttpStatus.NOT_FOUND.value(), null);
    }
}
