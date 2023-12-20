package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.exception.NotFoundException;
import com.coople.gamepartnerservice.model.GameRequest;
import com.coople.gamepartnerservice.model.GameRequestUpdate;
import com.coople.gamepartnerservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing game-related operations in the application.
 * This class is annotated with Spring's @Service, making it a Spring service bean.
 * It is annotated with Lombok's {@code @RequiredArgsConstructor} for constructor injection.w
 * It uses a GameRepository for database interactions.
 *
 * @see org.springframework.stereotype.Service
 * @see GameRepository
 * @see GameEntity
 * @see GameRequest
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Service
@RequiredArgsConstructor
public class GameService {

    /**
     * GameRepository for database interactions.
     */
    private final GameRepository gameRepository;

    /**
     * Saves a new game using the provided GameRequest.
     *
     * @param gameRequest The GameRequest containing information about the game to be saved.
     * @return The saved GameEntity.
     * @see GameEntity
     * @see GameRequest
     * @see GameRepository#save(Object)
     */
    public GameEntity saveGame(GameRequest gameRequest) {
        GameEntity gameEntity = GameEntity.builder()
                .title(gameRequest.getTitle())
                .platforms(gameRequest.getPlatforms())
                .genre(gameRequest.getGenre())
                .amountOfPlayers(gameRequest.getAmountOfPlayers())
                .build();

        return gameRepository.save(gameEntity);
    }

    /**
     * Retrieves all games from the database.
     *
     * @return A list of all GameEntity instances.
     * @see GameEntity
     * @see GameRepository#findAll()
     */
    public List<GameEntity> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Retrieves a game by its ID from the database.
     *
     * @param id The ID of the game to retrieve.
     * @return An Optional containing the retrieved GameEntity, or empty if not found.
     * @see GameEntity
     * @see GameRepository#findById(Object)
     */
    public Optional<GameEntity> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    /**
     * Retrieves a game by its ID from the database.
     *
     * @param title The Title of the game to retrieve.
     * @return An Optional containing the retrieved GameEntity, or empty if not found.
     * @see GameEntity
     * @see GameRepository#findById(Object)
     */
    public GameEntity getGameByTitle(String title) {
        return gameRepository.getGameEntityByTitle(title);
    }

    /**
     * Update existing game using the provided GameRequestUpdate.
     *
     * @param gameRequestUpdate The GameRequestUpdate containing information about the game to be updated.
     * @return The saved GameEntity.
     * @see GameEntity
     * @see GameRequest
     * @see GameRepository#save(Object)
     */
    public GameEntity updateGame(GameRequestUpdate gameRequestUpdate) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameRequestUpdate.getId());

        if (gameEntity.isPresent()) {
            GameEntity gameEntityUpdated = GameEntity.builder()
                    .id(gameRequestUpdate.getId())
                    .title(gameRequestUpdate.getTitle())
                    .description(gameRequestUpdate.getDescription())
                    .platforms(gameRequestUpdate.getPlatforms())
                    .genre(gameRequestUpdate.getGenre())
                    .amountOfPlayers(gameRequestUpdate.getAmountOfPlayers())
                    .build();

            return gameRepository.save(gameEntityUpdated);
        } else {
            throw new NotFoundException("Game does not exist. Game id: " + gameRequestUpdate.getId());
        }
    }

    /**
     * Deletes a game by its ID from the database.
     *
     * @param id The ID of the game to delete.
     * @see GameRepository#deleteById(Object)
     */
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }
}
