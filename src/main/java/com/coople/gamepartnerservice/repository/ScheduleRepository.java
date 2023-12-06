package com.coople.gamepartnerservice.repository;

import com.coople.gamepartnerservice.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
}
