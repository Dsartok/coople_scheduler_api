package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;
import com.coople.gamepartnerservice.model.RegistrationRequest;
import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user registration operations in the application.
 * This class is annotated with Spring's @Service, making it a Spring service bean.
 * It is annotated with Lombok's {@code @RequiredArgsConstructor} for constructor injection.
 * It uses a UserRepository for database interactions and a PasswordEncoder for password hashing.
 *
 * @see org.springframework.stereotype.Service
 * @see UserRepository
 * @see PasswordEncoder
 * @see UserEntity
 * @see RegistrationRequest
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {

    /**
     * UserRepository for database interactions.
     */
    private final UserRepository userRepository;

    /**
     * PasswordEncoder for password hashing.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user using the provided RegistrationRequest.
     *
     * @param request The RegistrationRequest containing information about the user to be registered.
     * @see UserRepository
     * @see PasswordEncoder
     * @see UserEntity
     * @see RegistrationRequest
     */
    public void registerUser(RegistrationRequest request) {
        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER") // Set default role for new users
                .build();

        userRepository.save(newUser);
    }
}
