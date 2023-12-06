package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.UserEntity;

import com.coople.gamepartnerservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findByEmail(String email) {

        UserEntity userEntity = userRepository.findByEmailIgnoreCase(email);

        if (userEntity != null && userEntity.getEmail().equals(email)) {
            return Optional.of(userEntity);
        } else {
            return Optional.empty();
        }
    }

    public UserEntity findByName(String name) {
        UserEntity userEntity = userRepository.findByNameIgnoreCase(name);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found by name: " + name);
        }

        return userEntity;
    }

    public Optional<UserEntity> findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found by id: " + id);
        }

        return userEntity;
    }
}
