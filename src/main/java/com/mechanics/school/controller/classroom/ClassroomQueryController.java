package com.mechanics.school.controller.classroom;

import com.mechanics.school.mapper.dtos.classroom.ClassroomDto;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.classroom.ClassroomQueryService;
import com.mechanics.school.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/classroom")
public class ClassroomQueryController {
    private final ClassroomQueryService classroomQueryService;

    @Autowired
    public ClassroomQueryController(ClassroomQueryService classroomQueryService) {
        this.classroomQueryService = classroomQueryService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GUARDIAN')")
    public Mono<ResponseEntity<Object>> getClassroom(@PathVariable Long id) {
        return Mono.fromCallable(() -> {
            ClassroomDto classroom = classroomQueryService.FindClassroomDtoById(id);
            if (classroom == null) {
                return ResponseHandler.responseBuilder(
                        "Classroom Type not found",
                        HttpStatus.NOT_FOUND.value(),
                        null
                );
            }
            return ResponseHandler.responseBuilder(
                    "Classroom Type found",
                    HttpStatus.OK.value(),
                    classroom
            );
        }).onErrorReturn(ResponseHandler.responseBuilder(
                "Error occurred during finding ClassroomDto by id.",
                HttpStatus.UNAUTHORIZED.value(),
                null
        ));
    }

    @GetMapping("")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT')")
    public Mono<ResponseEntity<Object>> getAllClassroom() {
        try {
            return Mono.just(ResponseHandler.responseBuilder(
                    "Classroom found",
                    HttpStatus.OK.value(),
                    classroomQueryService.FindAllClassroomDto()
            ));
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all ClassroomDto.", e);
            return Mono.just(ResponseHandler.responseBuilder(
                    "Error occurred during finding all ClassroomDto.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }

    @GetMapping("/search")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT')")
    public Mono<ResponseEntity<Object>> searchClassroom(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        try {
            return Mono.just(ResponseHandler.responseBuilder(
                    "Classroom found",
                    HttpStatus.OK.value(),
                    classroomQueryService.searchClassroomDto(id, name)
            ));
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding all ClassroomDto.", e);
            return Mono.just(ResponseHandler.responseBuilder(
                    "Error occurred during finding all ClassroomDto.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }
}
