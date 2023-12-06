package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.model.GameRequest;
import com.coople.gamepartnerservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public GameEntity saveGame(GameRequest gameRequest) {
        GameEntity gameEntity = GameEntity.builder()
                .title(gameRequest.getTitle())
                .platform(gameRequest.getPlatform())
                .genre(gameRequest.getGenre())
                .amountOfPlayers(gameRequest.getAmountOfPlayers())
                .build();

        return gameRepository.save(gameEntity);
    }

    public List<GameEntity> getAllGames() {
        return gameRepository.findAll();
    }

    public GameEntity getGameByTitle(String title) {
        return gameRepository.findByTitleIgnoreCase(title);
    }

    public Optional<GameEntity> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }
}
