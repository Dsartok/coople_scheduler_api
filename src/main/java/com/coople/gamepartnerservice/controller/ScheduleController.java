package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.entity.ScheduleEntity;
import com.coople.gamepartnerservice.model.ScheduleRequest;
import com.coople.gamepartnerservice.service.GameService;
import com.coople.gamepartnerservice.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to schedule entities.
 * Provides endpoints for retrieving, creating, and querying schedules.
 * Base URL is "/schedules".
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final GameService gameService;

    /**
     * Retrieve a list of all schedules.
     *
     * @return ResponseEntity with a list of ScheduleEntity objects and HTTP status OK (200).
     */
    @GetMapping("/")
    public ResponseEntity<List<ScheduleEntity>> getAllSchedules() {
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    /**
     * Retrieve a list of schedules by their start time.
     *
     * @param startTime The start time used for filtering schedules.
     * @return ResponseEntity with a list of ScheduleEntity objects and HTTP status OK (200).
     */
    @GetMapping("/byStartTime")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByStartTime(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime) {
        List<ScheduleEntity> schedules = scheduleService.getSchedulesByStartTime(startTime);
        return ResponseEntity.ok(schedules);
    }

    /**
     * Retrieve a list of schedules associated with a specific game.
     *
     * @param gameId The ID of the game used for filtering schedules.
     * @return ResponseEntity with a list of ScheduleEntity objects and HTTP status OK (200).
     * @throws EntityNotFoundException if the game with the specified ID is not found.
     */
    @GetMapping("/byGame")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByGame(@RequestParam Long gameId) {
        // Check if the game with the provided ID exists
        GameEntity existingGame = gameService.getGameById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));

        // Call the service method to get schedules by the specified game
        List<ScheduleEntity> schedules = scheduleService.getSchedulesByGame(existingGame);

        // Return the schedules in the response with HTTP status OK (200)
        return ResponseEntity.ok(schedules);
    }

    /**
     * Retrieve a specific schedule by its ID.
     *
     * @param id The ID of the schedule to retrieve.
     * @return ResponseEntity with the ScheduleEntity object and HTTP status OK (200),
     *         or HTTP status NOT_FOUND (404) if the schedule is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable Long id) {
        Optional<ScheduleEntity> schedule = scheduleService.getScheduleById(id);
        return schedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new schedule.
     *
     * @param scheduleRequest The request containing information about the new schedule.
     * @return ResponseEntity with the created ScheduleEntity object and HTTP status CREATED (201).
     */
    @PostMapping("/")
    public ResponseEntity<ScheduleEntity> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        ScheduleEntity createdSchedule = scheduleService.createSchedule(scheduleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    /**
     * Update an existing schedule based on the provided information.
     *
     * @param scheduleId       The ID of the schedule to update.
     * @param scheduleRequest  The request containing information about the updated schedule.
     * @return ResponseEntity with the updated ScheduleEntity object and HTTP status OK (200),
     *         or HTTP status NOT_FOUND (404) if the schedule is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(
            @PathVariable("id") Long scheduleId,
            @RequestBody ScheduleRequest scheduleRequest) {
        ScheduleEntity updatedSchedule = scheduleService.updateSchedule(scheduleId, scheduleRequest);
        return ResponseEntity.ok(updatedSchedule);
    }

    /**
     * Delete a schedule by its ID.
     *
     * @param id The ID of the schedule to delete.
     * @return ResponseEntity with HTTP status OK (200).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        if (scheduleService.scheduleExists(id)) {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add user as participant to the schedule by its ID.
     *
     * @param scheduleId The ID of the schedule.
     * @param userId The ID of the user as participant.
     * @return ResponseEntity with HTTP status OK (200).
     */
    @PostMapping("/{scheduleId}/participants/{userId}")
    public ResponseEntity<ScheduleEntity> addUserToSchedule(
            @PathVariable Long scheduleId,
            @PathVariable Long userId) {
        ScheduleEntity updatedSchedule = scheduleService.addUserToSchedule(userId, scheduleId);
        return ResponseEntity.ok(updatedSchedule);
    }

    /**
     * Delete user as participant from the schedule by its ID.
     *
     * @param scheduleId The ID of the schedule.
     * @param userId The ID of the user as participant.
     * @return ResponseEntity with HTTP status OK (200).
     */
    @DeleteMapping("/{scheduleId}/participants/{userId}")
    public ResponseEntity<ScheduleEntity> excludeUserFromSchedule(
            @PathVariable Long scheduleId,
            @PathVariable Long userId) {
        ScheduleEntity updatedSchedule = scheduleService.deleteUserFromSchedule(userId, scheduleId);
        return ResponseEntity.ok(updatedSchedule);
    }
}
