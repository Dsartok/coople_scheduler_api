package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.exception.NotFoundException;
import com.coople.gamepartnerservice.model.GameRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coople_scheduler_api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/get")
    public ResponseEntity<List<GameEntity>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GameEntity> getGameById(@PathVariable Long id) {
        GameEntity gameEntity = gameService.getGameById(id)
                .orElseThrow(() -> new NotFoundException("Game not found by id: " + id));

        return ResponseEntity.ok(gameEntity);
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<GameEntity> getGameByTitle(@PathVariable String title) {
        GameEntity gameEntity = gameService.getGameByTitle(title);

        if (gameEntity == null) {
            throw new NotFoundException("Game not found by title: " + title);
        }

        return ResponseEntity.ok(gameEntity);
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<GameEntity> addGame(@RequestBody GameRequest gameRequest) {

        GameEntity gameEntitySearch = gameService.getGameByTitle(gameRequest.getTitle());

        if (gameEntitySearch == null) {
            ResponseEntity.badRequest();
        }

        GameEntity gameEntity = gameService.saveGame(gameRequest);
        return new ResponseEntity<>(gameEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void deleteGame(@PathVariable Long id) {

        GameEntity gameEntity = gameService.getGameById(id)
                .orElseThrow(() -> new NotFoundException("Not found: " + id));

        gameService.deleteGameById(id);
    }
}
