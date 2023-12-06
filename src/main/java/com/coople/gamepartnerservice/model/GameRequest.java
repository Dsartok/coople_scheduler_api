package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameRequest {
    private final String title;
    private final String genre;
    private final String platform;
    private final int amountOfPlayers;
}
