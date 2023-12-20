package com.coople.gamepartnerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a schedule in the application.
 * This class is annotated with Lombok's @Getter and @Setter to automatically generate getters and setters.
 * It is also annotated as a JPA Entity, mapped to the "schedules" table.
 *
 * <p>The builder class, {@link ScheduleEntityBuilder}, is a nested static class providing a fluent interface for
 * constructing instances of {@link ScheduleEntity}.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Setter
@Entity
@Table(name = "schedules")
public class ScheduleEntity {

    /**
     * The unique identifier for the schedule.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the schedule.
     */
    @Column(nullable = false, unique = true)
    private String scheduleName;

    /**
     * The associated game for the schedule.
     */
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    /**
     * The user associated with the schedule.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * The start time of the schedule.
     */
    @Column(nullable = false)
    private LocalDateTime startTime;

    /**
     * The end time of the schedule (nullable).
     */
    @Column(nullable = true)
    private LocalDateTime endTime;

    /**
     * The number of players associated with the schedule.
     */
    @Column(nullable = false)
    private int amountOfPlayers;

    /**
     * The set of participants (type {UserEntity}) associated with the schedule.
     */
    @ManyToMany
    @JoinTable(
            name = "schedule_participants",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> participants = new HashSet<>();

    /**
     * Static builder method for creating instances of {@link ScheduleEntity}.
     *
     * @return A new instance of {@link ScheduleEntityBuilder}.
     */
    public static ScheduleEntityBuilder builder() {
        return new ScheduleEntityBuilder();
    }

    /**
     * Builder class for constructing instances of {@link ScheduleEntity}.
     */
    public static class ScheduleEntityBuilder {

        private Long id;
        private String scheduleName;
        private GameEntity game;
        private UserEntity user;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int amountOfPlayers;
        private Set<UserEntity> participants = new HashSet<>();

        ScheduleEntityBuilder() {
        }

        public ScheduleEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ScheduleEntityBuilder scheduleName(String scheduleName) {
            this.scheduleName = scheduleName;
            return this;
        }

        public ScheduleEntityBuilder game(GameEntity game) {
            this.game = game;
            return this;
        }

        public ScheduleEntityBuilder user(UserEntity user) {
            this.user = user;
            return this;
        }

        public ScheduleEntityBuilder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public ScheduleEntityBuilder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public ScheduleEntityBuilder amountOfPlayers(int amountOfPlayers) {
            this.amountOfPlayers = amountOfPlayers;
            return this;
        }

        public ScheduleEntityBuilder participants(Set<UserEntity> participants) {
            this.participants = participants;
            return this;
        }

        /**
         * Builds and returns a new instance of {@link ScheduleEntity}.
         *
         * @return A new instance of {@link ScheduleEntity}.
         */
        public ScheduleEntity build() {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setId(id);
            scheduleEntity.setScheduleName(scheduleName);
            scheduleEntity.setGame(game);
            scheduleEntity.setUser(user);
            scheduleEntity.setStartTime(startTime);
            scheduleEntity.setEndTime(endTime);
            scheduleEntity.setAmountOfPlayers(amountOfPlayers);
            scheduleEntity.setParticipants(participants);
            return scheduleEntity;
        }
    }
}
