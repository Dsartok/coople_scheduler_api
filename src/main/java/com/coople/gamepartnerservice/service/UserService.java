package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;

import com.coople.gamepartnerservice.exception.DuplicateEntityException;
import com.coople.gamepartnerservice.exception.NotFoundException;
import com.coople.gamepartnerservice.model.UserRequestUpdate;
import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

/**
 * Service class for managing user-related operations.
 * This class is annotated with Spring's @Service, making it a Spring service bean.
 * It is annotated with Lombok's {@code @RequiredArgsConstructor} for constructor injection.
 * It uses a UserRepository for database interactions.
 *
 * @see UserRepository
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves a user by their name.
     *
     * @param name The name of the user to retrieve.
     * @return Optional containing the UserEntity if found, otherwise empty.
     */
    public Optional<UserEntity> findByName(String name) {
        UserEntity userEntity = userRepository.findByNameIgnoreCase(name);

        if (userEntity != null && userEntity.getName().equals(name)) {
            return Optional.of(userEntity);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Retrieves all users in the system.
     *
     * @return List of UserEntity objects.
     */
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return Optional containing the UserEntity if found, otherwise empty.
     */
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Creates a new admin user based on the provided {@code userRequestUpdate}.
     *
     * @param userRequestUpdate The {@code userRequestUpdate} object containing admin user details for updating.
     * @param id The ID of the user to be updated.
     * @return The created {@code UserEntity}.
     * @throws NotFoundException if a user with id not found.
     */
    public UserEntity updateUserToAdmin(Long id, UserRequestUpdate userRequestUpdate) {
        // Validate if the user with the given ID exists
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        // Validate if updated name or email is not already associated with another user
        existingUser.setRole("ROLE_ADMIN");
        existingUser.setExtraInfo(userRequestUpdate.getExtraInfo());

        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    /**
     * Updates an existing user based on the provided ID and {@code UserRequestUpdate}.
     *
     * @param id The ID of the user to be updated.
     * @param userRequestUpdate The {@code UserRequestUpdate} object containing updated user details.
     * @return The updated {@code UserEntity}.
     * @throws NotFoundException if the user with the given ID is not found.
     * @throws DuplicateEntityException if an attempt to update the user results in duplicate name or email.
     */
    public UserEntity updateUser(Long id, UserRequestUpdate userRequestUpdate) {
        // Validate if the user with the given ID exists
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        // Validate if updated name or email is not already associated with another user
        validateUserNotExists(userRequestUpdate.getName(), userRequestUpdate.getEmail());

        // Update user entity
        existingUser.setExtraInfo(userRequestUpdate.getExtraInfo());

        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return deleted user.
     * @throws NotFoundException If the user is not found.
     */
    public UserEntity deleteUser(Long id) {
        UserEntity deletedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found. User id: " + id));

        userRepository.deleteById(id);
        return deletedUser;
    }

    /**
     * Validates that a user with the given name or email does not already exist, excluding the current user.
     *
     * @param name The name to be checked.
     * @param email The email to be checked.
     * @throws DuplicateEntityException if a user with the same name or email already exists (excluding the current user).
     */
    private void validateUserNotExists(String name, String email) {

        // Validate if the user with the given name or email already exists excluding the current user
        UserEntity userByName = userRepository.findByNameIgnoreCase(name);
        if (userByName != null) {
            throw new DuplicateEntityException("User with name " + name + " already exists.");
        }

        UserEntity userByEmail = userRepository.findByEmailIgnoreCase(email);
        if (userByEmail != null) {
            throw new DuplicateEntityException("User with email " + email + " already exists.");
        }
    }
}
