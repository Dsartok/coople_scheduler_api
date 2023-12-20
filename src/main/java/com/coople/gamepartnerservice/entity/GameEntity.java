package com.coople.gamepartnerservice.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a game in the application.
 * This class is annotated with Lombok's @Getter and @Setter to automatically generate getters and setters.
 * It is also annotated as a JPA Entity, mapped to the "games" table.
 *
 * <p>The builder class, {@link GameEntityBuilder}, is a nested static class providing a fluent interface for
 * constructing instances of {@link GameEntity}.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Setter
@Entity
@Table(name = "games")
public class GameEntity {

    /**
     * The unique identifier for the game.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the game.
     */
    @Column(nullable = false, unique = true)
    private String title;

    /**
     * The description of the game.
     */
    private String description;

    /**
     * The set of platforms on which the game is available.
     */
    @ElementCollection
    @CollectionTable(name = "platforms", joinColumns = @JoinColumn(name = "game_id"))
    private Set<String> platforms = new HashSet<>();

    /**
     * The genre of the game.
     */
    @Column(nullable = false)
    private String genre;

    /**
     * The amount of players the game supports.
     */
    @Column(nullable = false)
    private int amountOfPlayers;

    @OneToMany(mappedBy = "game")
    private Set<ScheduleEntity> schedules = new HashSet<>();

    /**
     * Static builder method for creating instances of {@link GameEntity}.
     *
     * @return A new instance of {@link GameEntityBuilder}.
     */
    public static GameEntityBuilder builder() {
        return new GameEntityBuilder();
    }

    /**
     * Builder class for constructing instances of {@link GameEntity}.
     */
    public static class GameEntityBuilder {

        private Long id;
        private String title;
        private String description;
        private Set<String> platforms = new HashSet<>();
        private String genre;
        private int amountOfPlayers;

        private Set<ScheduleEntity> schedules = new HashSet<>();

        GameEntityBuilder() {
        }

        public GameEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public GameEntityBuilder title(String title) {
            this.title = title;
            return this;
        }

        public GameEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public GameEntityBuilder platforms(Set<String> platforms) {
            this.platforms = platforms;
            return this;
        }

        public GameEntityBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public GameEntityBuilder amountOfPlayers(int amountOfPlayers) {
            this.amountOfPlayers = amountOfPlayers;
            return this;
        }

        public GameEntityBuilder schedules(Set<ScheduleEntity> schedules) {
            this.schedules = schedules;
            return this;
        }

        /**
         * Builds and returns a new instance of {@link GameEntity}.
         *
         * @return A new instance of {@link GameEntity}.
         */
        public GameEntity build() {
            GameEntity gameEntity = new GameEntity();
            gameEntity.setId(id);
            gameEntity.setTitle(title);
            gameEntity.setDescription(description);
            gameEntity.setPlatforms(platforms);
            gameEntity.setGenre(genre);
            gameEntity.setAmountOfPlayers(amountOfPlayers);
            return gameEntity;
        }
    }
}
