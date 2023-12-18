package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * A data transfer object (DTO) representing a request to create or update a schedule.
 * This class is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 *
 * <p>The fields in this class include information such as the schedule name, game title, participant requests,
 * start and end times, and the number of players. It also provides methods to convert start and end time strings
 * to LocalDateTime objects using a specific date-time formatter.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class ScheduleRequest {

    /**
     * The name of the schedule.
     */
    private String scheduleName;

    /**
     * The title of the game associated with the schedule.
     */
    private GameRequestSchedule GameRequestSchedule;

    /**
     * The participant request for a user owner associated with the schedule.
     */
    private UserRequestSchedule userRequestSchedule;

    /**
     * The start time of the schedule in string format.
     */
    private String startTime;

    /**
     * The end time of the schedule in string format.
     */
    private String endTime;

    /**
     * The number of players associated with the schedule.
     */
    private int amountOfPlayers;

    /**
     * The set of participant requests associated with the schedule.
     */
    private Set<UserRequestSchedule> participantRequestSchedule;

    /**
     * The date-time formatter for parsing start and end time strings.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Converts the start time string to a LocalDateTime object using the specified formatter.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getStartTime() {
        return LocalDateTime.parse(startTime, formatter);
    }

    /**
     * Converts the end time string to a LocalDateTime object using the specified formatter.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getEndTime() {
        return LocalDateTime.parse(endTime, formatter);
    }
}
