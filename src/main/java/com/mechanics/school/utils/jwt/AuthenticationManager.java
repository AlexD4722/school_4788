package com.mechanics.school.utils.jwt;

import com.mechanics.school.exception.UnauthorizedExceptionHandler;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private JWTUtil jwtUtil;

    @Override
    @SuppressWarnings("unchecked")
        public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            if (!jwtUtil.validateToken(authToken)) {
                return Mono.error(new UnauthorizedExceptionHandler("Invalid token", HttpStatus.UNAUTHORIZED));
            }
            String userName = jwtUtil.getUsernameFromToken(authToken);
            Logger.getLogger("AuthenticationManager").info("Username: " + userName);
            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
            List<String> rolesMap = claims.get("role", List.class);
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    userName,
                    null,
                    rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            ));
        } catch (Exception e) {
            return Mono.error(new UnauthorizedExceptionHandler("Invalid token", HttpStatus.UNAUTHORIZED));
        }
    }
}
