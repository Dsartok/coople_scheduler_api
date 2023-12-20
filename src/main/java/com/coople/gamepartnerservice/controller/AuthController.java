package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.model.LoginRequest;
import com.coople.gamepartnerservice.model.LoginResponse;
import com.coople.gamepartnerservice.model.RegistrationRequest;
import com.coople.gamepartnerservice.model.ResetPasswordRequest;
import com.coople.gamepartnerservice.service.AuthService;
import com.coople.gamepartnerservice.service.PasswordResetService;
import com.coople.gamepartnerservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling authentication-related endpoints.
 * This class is annotated with Spring's @RestController, indicating that it provides REST API services.
 * Annotated with @RequestMapping - base URL is "/auth".
 * The class is constructed using Lombok's @RequiredArgsConstructor, injecting the required services.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The authentication service responsible for handling login attempts.
     */
    private final AuthService authService;

    /**
     * The registration service responsible for user registration.
     */
    private final RegistrationService registrationService;

    /**
     * The password reset service responsible for resetting user passwords.
     */
    private final PasswordResetService passwordResetService;

    /**
     * Handles user login requests.
     *
     * @param request The login request containing user credentials.
     * @return A ResponseEntity containing the login response.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        LoginResponse loginResponse = authService.attemptLogin(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(loginResponse);
    }

    /**
     * Handles user registration requests.
     *
     * @param request The registration request containing user details.
     * @return A ResponseEntity with a success message.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated RegistrationRequest request) {
        registrationService.registerUser(request);

        return ResponseEntity.ok("User registered successfully for user: " + request.getEmail());
    }

    /**
     * Handles user password reset requests.
     *
     * @param request The reset password request containing user email and old/new passwords.
     * @return A ResponseEntity with a success message.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetService.resetPassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());

        return ResponseEntity.ok("Password reset successfully for user: " + request.getEmail());
    }
}
