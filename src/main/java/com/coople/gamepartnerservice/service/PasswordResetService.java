package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;
import com.coople.gamepartnerservice.exception.InvalidResetTokenException;
import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling password reset operations in the application.
 * This class is annotated with Spring's @Service, making it a Spring service bean.
 * It is annotated with Lombok's {@code @RequiredArgsConstructor} for constructor injection.
 * It uses a UserRepository for database interactions and a PasswordEncoder for password hashing.
 *
 * @see org.springframework.stereotype.Service
 * @see UserRepository
 * @see PasswordEncoder
 * @see UserEntity
 * @see InvalidResetTokenException
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Service
@RequiredArgsConstructor
public class PasswordResetService {

    /**
     * UserRepository for database interactions.
     */
    private final UserRepository userRepository;

    /**
     * PasswordEncoder for password hashing.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Resets the password for a user with the provided email.
     *
     * @param email        The email address of the user whose password needs to be reset.
     * @param oldPassword  The old password for validation.
     * @param newPassword  The new password to set after successful validation.
     * @throws InvalidResetTokenException If the old password is invalid or the user is not found.
     * @see UserRepository
     * @see PasswordEncoder
     * @see UserEntity
     * @see InvalidResetTokenException
     */
    public void resetPassword(String email, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findByEmailIgnoreCase(email);

        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {

            // Set the new password
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

        } else {
            throw new InvalidResetTokenException("Invalid old password or user not found.");
        }
    }
}
