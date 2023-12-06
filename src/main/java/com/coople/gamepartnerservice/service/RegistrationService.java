package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;
import com.coople.gamepartnerservice.model.RegistrationRequest;
import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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