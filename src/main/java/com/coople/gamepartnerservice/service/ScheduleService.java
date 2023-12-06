package com.coople.gamepartnerservice.service;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.entity.ScheduleEntity;
import com.coople.gamepartnerservice.entity.UserEntity;
import com.coople.gamepartnerservice.model.ScheduleRequest;
import com.coople.gamepartnerservice.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final GameService gameService;

    public ScheduleEntity createSchedule(ScheduleRequest scheduleRequest) {
        // Find game from GameRepository
        String gameTitle = scheduleRequest.getGameTitle();
        GameEntity gameEntity = gameService.getGameByTitle(gameTitle);

        // Find all participants from UserRepository
        Set<UserEntity> userEntitiesParticipants = scheduleRequest.getParticipants().stream()
                .map(participant -> userService.findByName(participant.getName()))
                .collect(Collectors.toSet());

        // Find admin
        String adminName = scheduleRequest.getParticipantRequestAsAdminUser().getName();
        UserEntity adminUserEntity = userService.findByName(adminName);

        // Create scheduleEntity
        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .adminUser(adminUserEntity)
                .scheduleName(scheduleRequest.getScheduleName())
                .amountOfPlayers(scheduleRequest.getAmountOfPlayers())
                .participants(userEntitiesParticipants)
                .startTime(scheduleRequest.getStartTime())
                .endTime(scheduleRequest.getEndTime())
                .game(gameEntity)
                .build();

        return scheduleRepository.save(scheduleEntity);
    }

    public ScheduleEntity getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + id));
    }

    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void addUserToSchedule(Long scheduleId, String name) {
        ScheduleEntity scheduleEntity = getScheduleById(scheduleId);
        UserEntity userEntity = userService.findByName(name);

        // Perform validation

        scheduleEntity.getParticipants().add(userEntity);
        scheduleRepository.save(scheduleEntity);
    }

    public void removeUserFromSchedule(Long scheduleId, String name) {
        ScheduleEntity scheduleEntity = getScheduleById(scheduleId);
        UserEntity userEntity = userService.findByName(name);

        // Perform validation

        scheduleEntity.getParticipants().add(userEntity);
        scheduleRepository.save(scheduleEntity);
    }
}