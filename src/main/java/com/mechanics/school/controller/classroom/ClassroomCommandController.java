package com.mechanics.school.controller.classroom;

import com.mechanics.school.mapper.dtos.classroom.CreateClassroomDto;
import com.mechanics.school.mapper.dtos.classroom.ModifyClassroomDto;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.classroom.ClassroomCommandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/classroom")
public class ClassroomCommandController {
    private ClassroomCommandService classroomCommandService;

    public ClassroomCommandController(ClassroomCommandService classroomCommandService) {
        this.classroomCommandService = classroomCommandService;
    }

    @PostMapping("")
//    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<Object> insertClassroom(@Valid @RequestBody CreateClassroomDto createClassroomDto) {
        if (classroomCommandService.insert(createClassroomDto)) {
            return ResponseHandler.responseBuilder(
                    "Classroom inserted",
                    HttpStatus.CREATED.value(),
                    createClassroomDto
            );
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during inserting Classroom",
                HttpStatus.BAD_REQUEST.value(),
                null
        );
    }

    @PutMapping("")
    public ResponseEntity<Object> modifyClassroom(@Valid @RequestBody ModifyClassroomDto modifyClassroomDto) {
        if (classroomCommandService.modify(modifyClassroomDto)) {
            return ResponseHandler.responseBuilder(
                    "Classroom modified",
                    HttpStatus.OK.value(),
                    null
            );
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during modifying Classroom",
                HttpStatus.CONFLICT.value(),
                null
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClassroom(@PathVariable Long id) {
        if (classroomCommandService.delete(id)) {
            return ResponseHandler.responseBuilder(
                    "Classroom deleted",
                    HttpStatus.NO_CONTENT.value(),
                    null
            );
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during deleting Classroom",
                HttpStatus.CONFLICT.value(),
                null
        );
    }
}
