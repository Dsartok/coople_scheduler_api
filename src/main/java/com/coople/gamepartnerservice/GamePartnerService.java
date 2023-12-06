package com.coople.gamepartnerservice;

import com.coople.gamepartnerservice.model.RegistrationRequest;
import com.coople.gamepartnerservice.service.AuthService;
import com.coople.gamepartnerservice.service.RegistrationService;
import com.coople.gamepartnerservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The main class of the Game Partner Service application.
 *
 * <p>The {@code GamePartnerService} class initializes and configures the Spring Boot application context,
 * enabling the execution of the application.
 *
 */
@SpringBootApplication
public class GamePartnerService {

    /**
     * The entry point for launching the Game Partner Service application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(GamePartnerService.class, args);
    }

    /**
     * Custom {@link CommandLineRunner} bean that performs initial setup during application startup.
     * It registers an admin user and a regular user if they do not exist, and prints authentication tokens for both.
     *
     * @param userService The service for user-related operations.
     * @param registrationService The service for user registration.
     * @param authService The service for authentication operations.
     * @return {@link CommandLineRunner} instance that performs the necessary setup.
     */
    @Bean
    public CommandLineRunner commandLineRunner(UserService userService,
                                               RegistrationService registrationService,
                                               AuthService authService) {
        return args -> {

            // Admin registration
            var adminEmail = "admin@mail.com";
            var adminPassword = "AdminPassword";
            if (userService.findByEmail(adminEmail).isEmpty()) {
                var admin = RegistrationRequest.builder()
                        .name("Admin")
                        .email(adminEmail)
                        .password(adminPassword)
                        .build();

                registrationService.registerUser(admin);
            } else {
                System.out.println("Admin already exists. Skipping registration.");
            }

            System.out.println("Token Admin: " + authService.attemptLogin(adminEmail, adminPassword)
                               + ". Check role.");

            // User registration
            var userEmail = "user@mail.com";
            var userPassword = "UserPassword";
            if (userService.findByEmail(userEmail).isEmpty()) {
                var user = RegistrationRequest.builder()
                        .name("User")
                        .email(userEmail)
                        .password(userPassword)
                        .build();

                registrationService.registerUser(user);
            } else {
                System.out.println("User already exists. Skipping registration.");
            }

            System.out.println("Token User: " + authService.attemptLogin(adminEmail, adminPassword)
                               + ". Check role.");
        };
    }
}
