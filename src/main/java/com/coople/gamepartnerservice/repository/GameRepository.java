package com.coople.gamepartnerservice.repository;

import com.coople.gamepartnerservice.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for managing game entities in the database.
 * This interface extends Spring Data JPA's JpaRepository, providing basic CRUD operations.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity getGameEntityByTitle(String title);

    GameEntity getGameEntityById(Long id);
}
