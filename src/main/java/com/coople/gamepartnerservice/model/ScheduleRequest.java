package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Builder
public class ScheduleRequest {
    private String scheduleName;
    private String gameTitle;
    private ParticipantRequest participantRequestAsAdminUser;
    private String startTime;
    private String endTime;
    private int amountOfPlayers;
    private Set<ParticipantRequest> participants;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTime getStartTime() {
        return LocalDateTime.parse(startTime, formatter);
    }

    public LocalDateTime getEndTime() {
        return LocalDateTime.parse(endTime, formatter);
    }
}