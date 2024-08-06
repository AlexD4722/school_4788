package com.mechanics.school.controller.auth;

import com.mechanics.school.mapper.dtos.auth.AuthEmailRequest;
import com.mechanics.school.mapper.dtos.auth.AuthRequest;
import com.mechanics.school.mapper.dtos.auth.AuthResponse;
import com.mechanics.school.mapper.dtos.auth.AuthToken;
import com.mechanics.school.responses.ResponseHandler;
import com.mechanics.school.service.email.AuthEmailService;
import com.mechanics.school.service.user.UserQueryService;
import com.mechanics.school.utils.jwt.JWTUtil;
import com.mechanics.school.utils.jwt.PBKDF2Encoder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationREST {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserQueryService userQueryService;
    private final AuthEmailService authEmailService;

    @Autowired
    public AuthenticationREST(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserQueryService userQueryService, AuthEmailService authEmailService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userQueryService = userQueryService;
        this.authEmailService = authEmailService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody AuthRequest ar) {
        return userQueryService.findAuthUserDtoByUser_name(ar.getUserName())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseHandler.responseBuilder(
                        "login successful",
                        HttpStatus.OK.value(),
                        new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(
                        ResponseHandler.responseBuilder(
                                "login failed",
                                HttpStatus.UNAUTHORIZED.value(),
                                null))
                );
    }

    @PostMapping("/authenticateToken")
    public ResponseEntity<Object> authenticateToken(@RequestBody AuthToken authToken) {
        if (jwtUtil.validateToken(authToken.getToken())) {
            return ResponseHandler.responseBuilder(
                    "Token is valid",
                    HttpStatus.OK.value(),
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
    }

    @PostMapping("/email")
    public ResponseEntity<Object> modify(@RequestBody @Valid AuthEmailRequest emailRequest) {
        if (authEmailService.authEmail(emailRequest.getEmail(), emailRequest.getCode())) {
            return ResponseHandler.responseBuilder(
                    "Email verified successfully",
                    HttpStatus.OK.value(),
                    null
            );
        }
        return ResponseHandler.responseBuilder(
                "Error occurred during email verification",
                HttpStatus.BAD_REQUEST.value(),
                null
        );
    }
}