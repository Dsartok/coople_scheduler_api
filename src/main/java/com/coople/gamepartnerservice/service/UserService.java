package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;

import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findByEmail(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity.getEmail().equals(email)) {
            return Optional.of(userEntity);
        } else {
            return Optional.empty();
        }
    }
}
