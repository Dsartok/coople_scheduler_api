package com.coople.gamepartnerservice.repository;

import com.coople.gamepartnerservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByNameIgnoreCase(String name);

    UserEntity findByEmailIgnoreCase(String email);
}
