package com.coople.gamepartnerservice.repository;

import com.coople.gamepartnerservice.entity.GameEntity;
import com.coople.gamepartnerservice.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Repository interface for managing schedule entities in the database.
 * This interface extends Spring Data JPA's JpaRepository, providing basic CRUD operations.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByGameId(Long id);

    List<ScheduleEntity> findByParticipantsIdIn(Set<Long> participantIds);

    List<ScheduleEntity> getScheduleEntitiesByStartTime(LocalDateTime startTime);

    List<ScheduleEntity> getScheduleEntitiesByGame(GameEntity game);

    // Additional method for checking if a schedule with the specified ID exists
    boolean existsById(Long id);

    // Additional method for deleting a schedule by its ID
    void deleteById(Long id);
}
