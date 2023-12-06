package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.model.LoginRequest;
import com.coople.gamepartnerservice.model.LoginResponse;
import com.coople.gamepartnerservice.model.RegistrationRequest;
import com.coople.gamepartnerservice.model.ResetPasswordRequest;
import com.coople.gamepartnerservice.service.AuthService;
import com.coople.gamepartnerservice.service.PasswordResetService;
import com.coople.gamepartnerservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coople_scheduler_api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegistrationService registrationService;
    private final PasswordResetService passwordResetService;

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        LoginResponse loginResponse = authService.attemptLogin(request.getEmail(), request.getPassword());

        logger.info("login request");

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated RegistrationRequest request) {
        registrationService.registerUser(request);

        logger.info("register request");

        return ResponseEntity.ok("User registered successfully for user: " + request.getEmail());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetService.resetPassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());

        logger.info("resetPassword request");

        return ResponseEntity.ok("Password reset successfully for user: " + request.getEmail());
    }
}
