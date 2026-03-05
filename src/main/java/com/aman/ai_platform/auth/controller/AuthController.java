package com.aman.ai_platform.auth.controller;

import com.aman.ai_platform.auth.dto.request.LoginRequestDTO;
import com.aman.ai_platform.auth.dto.request.RegisterRequestDTO;
import com.aman.ai_platform.auth.dto.response.LoginResponseDTO;
import com.aman.ai_platform.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Tag(name = "Auth", description = "Authentication endpoints")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
        

        private final AuthService authService;

        public AuthController(AuthService authService) {
            this.authService = authService;
        }

        @PostMapping("/login")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Login", description = "Authenticate with email/password and return a JWT token")
        public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
            return authService.authenticate(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        }

            @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Register", description = "Register user with email/password and then login again.")
        public void register(@RequestBody RegisterRequestDTO registerRequestDTO) {
            authService.register(registerRequestDTO);
        }

}
