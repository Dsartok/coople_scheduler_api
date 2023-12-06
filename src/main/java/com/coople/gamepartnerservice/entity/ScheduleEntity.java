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

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String scheduleName;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "admin_user_id", nullable = false)
    private UserEntity adminUser;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int amountOfPlayers;

    @ManyToMany
    @JoinTable(
            name = "schedule_participants",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> participants = new HashSet<>();

    // Static builder method for ScheduleEntity
    public static ScheduleEntityBuilder builder() {
        return new ScheduleEntityBuilder();
    }

    // Builder class for ScheduleEntity
    public static class ScheduleEntityBuilder {

        private Long id;
        private String scheduleName;
        private GameEntity game;
        private UserEntity adminUser;
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

        public ScheduleEntityBuilder adminUser(UserEntity adminUser) {
            this.adminUser = adminUser;
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

        public ScheduleEntity build() {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setId(id);
            scheduleEntity.setScheduleName(scheduleName);
            scheduleEntity.setGame(game);
            scheduleEntity.setAdminUser(adminUser);
            scheduleEntity.setStartTime(startTime);
            scheduleEntity.setEndTime(endTime);
            scheduleEntity.setAmountOfPlayers(amountOfPlayers);
            scheduleEntity.setParticipants(participants);
            return scheduleEntity;
        }
    }
}