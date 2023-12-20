package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * A data transfer object (DTO) representing a request to update a game.
 * This class is immutable and is typically used to transfer data between layers of an application.
 * It is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class GameRequestUpdate {

    /**
     * The title of the game.
     */
    private final Long id;

    /**
     * The title of the game.
     */
    private final String title;

    /**
     * The description of the game.
     */
    private String description;

    /**
     * The set of platforms on which the game is available.
     */
    private final Set<String> platforms;

    /**
     * The genre of the game.
     */
    private final String genre;

    /**
     * The amount of players the game supports.
     */
    private final int amountOfPlayers;
}
