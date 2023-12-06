package com.coople.gamepartnerservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipantRequest {

    private String name;

    // No-arg constructor
    public ParticipantRequest() {
    }

    @JsonCreator
    public ParticipantRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}
