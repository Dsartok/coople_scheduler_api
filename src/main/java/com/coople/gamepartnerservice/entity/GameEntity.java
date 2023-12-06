package com.coople.gamepartnerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int amountOfPlayers;

    // Static builder method for gameService
    public static GameEntityBuilder builder() {
        return new GameEntityBuilder();
    }

    // Builder class for GameEntity
    public static class GameEntityBuilder {

        private Long id;
        private String title;
        private String description;
        private String platform;
        private String genre;
        private int amountOfPlayers;

        GameEntityBuilder() {
        }

        public GameEntity.GameEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public GameEntity.GameEntityBuilder title(String title) {
            this.title = title;
            return this;
        }

        public GameEntity.GameEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public GameEntity.GameEntityBuilder platform(String platform) {
            this.platform = platform;
            return this;
        }

        public GameEntity.GameEntityBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public GameEntity.GameEntityBuilder amountOfPlayers(int amountOfPlayers) {
            this.amountOfPlayers = amountOfPlayers;
            return this;
        }

        public GameEntity build() {
            GameEntity gameEntity = new GameEntity();
            gameEntity.setId(id);
            gameEntity.setTitle(title);
            gameEntity.setDescription(description);
            gameEntity.setPlatform(platform);
            gameEntity.setGenre(genre);
            gameEntity.setAmountOfPlayers(amountOfPlayers);
            return gameEntity;
        }
    }
}
