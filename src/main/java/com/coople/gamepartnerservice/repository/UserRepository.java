package com.coople.gamepartnerservice.repository;

import com.coople.gamepartnerservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing user entities in the database.
 * This interface extends Spring Data JPA's JpaRepository, providing basic CRUD operations.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByNameIgnoreCase(String name);

    UserEntity findByEmailIgnoreCase(String email);
}
