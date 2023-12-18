package com.coople.gamepartnerservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * A data transfer object (DTO) representing a request to create or update a participant.
 * This class is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 * It also includes a no-arg constructor and a constructor annotated with Jackson's @JsonCreator for JSON deserialization.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class ParticipantRequest {

    /**
     * The name of the participant.
     */
    private String name;

    /**
     * No-arg constructor.
     */
    public ParticipantRequest() {
    }

    /**
     * Constructor with a single parameter, annotated with Jackson's @JsonCreator for JSON deserialization.
     *
     * @param name The name of the participant.
     */
    @JsonCreator
    public ParticipantRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}
