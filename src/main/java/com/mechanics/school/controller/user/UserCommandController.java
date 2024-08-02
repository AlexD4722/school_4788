package com.mechanics.school.controller.user;

import com.mechanics.school.mapper.dtos.user.UserCreateDto;
import com.mechanics.school.mapper.dtos.user.UserModifyDto;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.user.UserCommandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class UserCommandController {
    private final UserCommandService userCommandService;

    public UserCommandController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    // Registering a user
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserCreateDto userCreateDto) {
        if (userCommandService.register(userCreateDto)) {
            return ResponseHandler.responseBuilder(
                    "User registered successfully",
                    HttpStatus.CREATED.value(),
                    null
            );
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during user registration",
                HttpStatus.BAD_REQUEST.value(),
                null
        );
    }
}
