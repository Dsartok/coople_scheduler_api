package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.entity.ScheduleEntity;
import com.coople.gamepartnerservice.entity.UserEntity;
import com.coople.gamepartnerservice.model.ScheduleRequest;
import com.coople.gamepartnerservice.model.UserRequestSchedule;
import com.coople.gamepartnerservice.repository.GameRepository;
import com.coople.gamepartnerservice.repository.ScheduleRepository;
import com.coople.gamepartnerservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing schedules in the application.
 * This class is annotated with Spring's @Service, making it a Spring service bean.
 * It is annotated with Lombok's {@code @RequiredArgsConstructor} for constructor injection.
 * It uses a ScheduleRepository, UserRepository, GameRepository for database interactions.
 *
 * @see org.springframework.stereotype.Service
 * @see UserRepository
 * @see ScheduleRepository
 * @see GameRepository
 * @see UserEntity
 * @see ScheduleEntity
 * @see GameEntity
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    /**
     * Creates a new schedule based on the provided ScheduleRequest.
     *
     * @param scheduleRequest The ScheduleRequest containing information about the schedule to be created.
     * @return The created ScheduleEntity.
     * @throws EntityNotFoundException If the associated game or user is not found, or if one or more participants are not found.
     */
    public ScheduleEntity createSchedule(ScheduleRequest scheduleRequest) {
        // Validate game existence
        GameEntity game = gameRepository.findById(scheduleRequest.getGameRequestSchedule().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Game with ID "
                                                    + scheduleRequest.getGameRequestSchedule().getId()
                                                    + " not found"));

        // Validate user existence
        UserEntity user = userRepository.findById(scheduleRequest.getUserRequestSchedule().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("User with ID "
                                                    + scheduleRequest.getUserRequestSchedule().getId()
                                                    + " not found"));

        // Validate and add participants
        Set<UserEntity> participants = null;
        if (scheduleRequest.getParticipantRequestSchedule() != null) {
            Set<Long> participantIds = scheduleRequest.getParticipantRequestSchedule()
                    .stream()
                    .map(UserRequestSchedule::getId)
                    .collect(Collectors.toSet());

            participants = validateAndLoadParticipants(participantIds);
        }

        // Create ScheduleEntity
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setScheduleName(scheduleRequest.getScheduleName());
        schedule.setGame(game);
        schedule.setUser(user);
        schedule.setStartTime(scheduleRequest.getStartTime());
        schedule.setEndTime(scheduleRequest.getEndTime());
        schedule.setAmountOfPlayers(scheduleRequest.getAmountOfPlayers());
        schedule.setParticipants(participants);

        // Save schedule
        return scheduleRepository.save(schedule);
    }

    // ADD USER TO SCHEDULE

    /**
     * Validates and loads participants based on their IDs.
     *
     * @param participantIds Set of participant IDs to be validated and loaded.
     * @return Set of UserEntity representing the participants.
     * @throws EntityNotFoundException If one or more participants are not found.
     */
    private Set<UserEntity> validateAndLoadParticipants(Set<Long> participantIds) {
        // Validate and load participants
        Set<UserEntity> participants = new HashSet<>(userRepository.findAllById(participantIds));

        if (participants.size() != participantIds.size()) {
            throw new EntityNotFoundException("One or more participants not found");
        }

        return participants;
    }

    /**
     * Retrieves a schedule by its ID.
     *
     * @param id The ID of the schedule to retrieve.
     * @return Optional containing the ScheduleEntity if found, otherwise empty.
     */
    public Optional<ScheduleEntity> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    /**
     * Retrieves all schedules.
     *
     * @return List of all ScheduleEntity.
     */
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    /**
     * Retrieves schedules based on their start time.
     *
     * @param startTime The start time to filter schedules.
     * @return List of ScheduleEntity with the specified start time.
     */
    public List<ScheduleEntity> getSchedulesByStartTime(LocalDateTime startTime) {
        return scheduleRepository.getScheduleEntitiesByStartTime(startTime);
    }

    /**
     * Retrieves schedules based on the associated game.
     *
     * @param game The associated GameEntity to filter schedules.
     * @return List of ScheduleEntity associated with the specified game.
     */
    public List<ScheduleEntity> getSchedulesByGame(GameEntity game) {
        return scheduleRepository.getScheduleEntitiesByGame(game);
    }

    /**
     * Update an existing schedule based on the provided information.
     *
     * @param scheduleId       The ID of the schedule to update.
     * @param scheduleRequest  The request containing information about the updated schedule.
     * @return The updated ScheduleEntity.
     * @throws EntityNotFoundException if the schedule with the specified ID is not found.
     */
    public ScheduleEntity updateSchedule(Long scheduleId, ScheduleRequest scheduleRequest) {
        ScheduleEntity existingSchedule = getScheduleById(scheduleId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Schedule not found with ID: " + scheduleId));

        GameEntity gameEntity = gameRepository
                .getGameEntityById(scheduleRequest.getGameRequestSchedule().getId());

        if (gameEntity == null) {
            throw new EntityNotFoundException("Game not found with ID: "
                                              + scheduleRequest.getGameRequestSchedule().getId());
        }

        // Validate and add participants
        Set<UserEntity> participants = null;
        if (scheduleRequest.getParticipantRequestSchedule() != null) {
            Set<Long> participantIds = scheduleRequest.getParticipantRequestSchedule()
                    .stream()
                    .map(UserRequestSchedule::getId)
                    .collect(Collectors.toSet());

            participants = validateAndLoadParticipants(participantIds);
        }

        // Update schedule fields based on the scheduleRequest
        existingSchedule.setScheduleName(scheduleRequest.getScheduleName());
        existingSchedule.setGame(gameEntity);
        existingSchedule.setStartTime(scheduleRequest.getStartTime());
        existingSchedule.setEndTime(scheduleRequest.getEndTime());
        existingSchedule.setAmountOfPlayers(scheduleRequest.getAmountOfPlayers());
        existingSchedule.setParticipants(participants);

        return scheduleRepository.save(existingSchedule);
    }

    /**
     * Check if a schedule with the specified ID exists.
     *
     * @param scheduleId The ID of the schedule to check.
     * @return true if the schedule exists, false otherwise.
     */
    public boolean scheduleExists(Long scheduleId) {
        return scheduleRepository.existsById(scheduleId);
    }

    /**
     * Delete a schedule by its ID.
     *
     * @param scheduleId The ID of the schedule to delete.
     * @throws EntityNotFoundException if the schedule with the specified ID is not found.
     */
    public void deleteSchedule(Long scheduleId) {
        ScheduleEntity schedule = getScheduleById(scheduleId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Schedule not found with ID: " + scheduleId));

        scheduleRepository.delete(schedule);
    }

    /**
     * Add user as participant to the schedule by its ID.
     *
     * @param scheduleId The ID of the schedule.
     * @param userId The ID of the user as participant.
     * @throws EntityNotFoundException if the schedule with the specified ID or User ID is not found.
     * @throws IllegalArgumentException if the user is already in this schedule.
     */
    public ScheduleEntity addUserToSchedule(Long userId, Long scheduleId) {
        // Find the schedule
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule with ID " + scheduleId + " not found"));

        // Find the user
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        // Check if the user is already a participant
        if (schedule.getParticipants().contains(user)) {
            throw new IllegalArgumentException("User is already a participant in this schedule");
        }

        // Add the user to the schedule
        schedule.getParticipants().add(user);

        // Save the updated schedule
        return scheduleRepository.save(schedule);
    }

    /**
     * Delete user as participant from the schedule by its ID.
     *
     * @param scheduleId The ID of the schedule.
     * @param userId The ID of the user as participant.
     * @throws EntityNotFoundException if the schedule with the specified ID or User ID is not found.
     * @throws IllegalArgumentException if the user is not in this schedule.
     */
    public ScheduleEntity deleteUserFromSchedule(Long userId, Long scheduleId) {
        // Find the schedule
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule with ID " + scheduleId + " not found"));

        // Find the user
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        // Check if the user is a participant
        if (!schedule.getParticipants().contains(user)) {
            throw new IllegalArgumentException("User is not a participant in this schedule");
        }

        // Exclude the user from the schedule
        schedule.getParticipants().remove(user);

        // Save the updated schedule
        return scheduleRepository.save(schedule);
    }
}
