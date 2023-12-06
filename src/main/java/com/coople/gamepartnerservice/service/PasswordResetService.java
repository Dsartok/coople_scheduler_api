package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;
import com.coople.gamepartnerservice.exception.InvalidResetTokenException;
import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
