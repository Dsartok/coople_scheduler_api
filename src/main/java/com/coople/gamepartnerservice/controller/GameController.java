package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.exception.NotFoundException;
import com.coople.gamepartnerservice.model.GameRequest;
import com.coople.gamepartnerservice.model.GameRequestUpdate;
import com.coople.gamepartnerservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to game entities.
 * Provides endpoints for retrieving, creating, updating, and deleting games.
 * Base URL is "/games".
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    /**
     * Retrieve a list of all games.
     *
     * @return ResponseEntity with a list of GameEntity objects and HTTP status OK (200).
     */
    @GetMapping("/")
    public ResponseEntity<List<GameEntity>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    /**
     * Retrieve a specific game by its ID.
     *
     * @param id The ID of the game to retrieve.
     * @return ResponseEntity with the GameEntity object and HTTP status OK (200).
     * @throws NotFoundException if the game with the specified ID is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameEntity> getGameById(@PathVariable Long id) {
        GameEntity gameEntity = gameService.getGameById(id)
                .orElseThrow(() -> new NotFoundException("Game not found. Game id: " + id));

        return ResponseEntity.ok(gameEntity);
    }

    /**
     * Retrieve a specific game by its title.
     *
     * @param title The title of the game to retrieve.
     * @return ResponseEntity with the GameEntity object and HTTP status OK (200).
     * @throws NotFoundException if the game with the specified title is not found.
     */
    @GetMapping("/{title}")
    public ResponseEntity<GameEntity> getGameByTitle(@PathVariable String title) {
        GameEntity gameEntity = gameService.getGameByTitle(title);

        if (gameEntity == null) {
            throw new NotFoundException("Game not found. Game title: " + title);
        }

        return ResponseEntity.ok(gameEntity);
    }

    /**
     * Create a new game.
     * Requires the 'ADMIN' role for access.
     *
     * @param gameRequest The request containing information about the new game.
     * @return ResponseEntity with the created GameEntity object and HTTP status CREATED (201).
     */
    @PostMapping("/")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<GameEntity> saveGame(@RequestBody GameRequest gameRequest) {
        GameEntity gameEntity = gameService.saveGame(gameRequest);
        return new ResponseEntity<>(gameEntity, HttpStatus.CREATED);
    }

    /**
     * Update an existing game.
     * Requires the 'ADMIN' role for access.
     *
     * @param gameRequestUpdate The request containing updated information about the game.
     * @return ResponseEntity with the updated GameEntity object and HTTP status OK (200).
     */
    @PutMapping("/")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<GameEntity> updateGame(@RequestBody GameRequestUpdate gameRequestUpdate) {
        GameEntity gameEntity = gameService.updateGame(gameRequestUpdate);
        return new ResponseEntity<>(gameEntity, HttpStatus.OK);
    }

    /**
     * Delete a game by its ID.
     * Requires the 'ADMIN' role for access.
     *
     * @param id The ID of the game to delete.
     * @return ResponseEntity with the deleted GameEntity object and HTTP status OK (200).
     * @throws NotFoundException if the game with the specified ID is not found.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<GameEntity> deleteGame(@PathVariable Long id) {
        GameEntity gameEntity = gameService.getGameById(id)
                .orElseThrow(() -> new NotFoundException("Game not found. Game id: " + id));

        gameService.deleteGameById(id);

        return new ResponseEntity<>(gameEntity, HttpStatus.OK);
    }
}
