package com.coople.gamepartnerservice;

import com.coople.gamepartnerservice.model.RegistrationRequest;
import com.coople.gamepartnerservice.model.UserRequestUpdate;
import com.coople.gamepartnerservice.repository.UserRepository;
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
                                               AuthService authService,
                                               UserRepository userRepository) {
        return args -> {

            // Admin registration
            var adminEmail = "admin@mail.com";
            var adminPassword = "AdminPassword";
            var adminName = "Admin";
            if (userService.findByName(adminName).isEmpty()) {

                // Register new admin
                var admin = RegistrationRequest.builder()
                        .name(adminName)
                        .email(adminEmail)
                        .password(adminPassword)
                        .build();

                registrationService.registerUser(admin);

                // Change role from "ROLE_USER" to "ROLE_ADMIN"
                UserRequestUpdate userRequestUpdate = UserRequestUpdate.builder()
                        .email(adminEmail)
                        .name(adminName)
                        .password(adminPassword)
                        .build();

                Long adminId = userRepository.findByEmailIgnoreCase(adminEmail).getId();

                userService.updateUserToAdmin(adminId, userRequestUpdate);
            } else {
                System.out.println("Admin already exists. Skipping registration.");
            }

            System.out.println("Token Admin: " + authService.attemptLogin(adminName, adminPassword));

            // User registration
            var userEmail = "user@mail.com";
            var userPassword = "UserPassword";
            var userName = "User";
            if (userService.findByName(userName).isEmpty()) {
                var user = RegistrationRequest.builder()
                        .name(userName)
                        .email(userEmail)
                        .password(userPassword)
                        .build();

                registrationService.registerUser(user);
            } else {
                System.out.println("User already exists. Skipping registration.");
            }

            System.out.println("Token User: " + authService.attemptLogin(userName, userPassword));
        };
    }
}
