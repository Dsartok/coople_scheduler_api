package com.coople.gamepartnerservice.repository;

import com.coople.gamepartnerservice.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.*;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

    // Create
    GameEntity save(GameEntity game);

    // Read
    List<GameEntity> findAll();
    Optional<GameEntity> findById(Long id);
    GameEntity findByTitleIgnoreCase(String title);

    List<GameEntity> findByGenreIgnoreCase(String genre);

    // Update
    GameEntity saveAndFlush(GameEntity game);

    // Delete
    void deleteById(Long id);
    void delete(GameEntity game);
}
