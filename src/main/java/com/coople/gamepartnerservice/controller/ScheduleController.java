package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.entity.ScheduleEntity;
import com.coople.gamepartnerservice.model.ScheduleRequest;
import com.coople.gamepartnerservice.security.UserPrincipal;
import com.coople.gamepartnerservice.service.ScheduleService;
import com.coople.gamepartnerservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/coople_scheduler_api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ScheduleEntity> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        ScheduleEntity createdSchedule = scheduleService.createSchedule(scheduleRequest);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable Long id) {
        ScheduleEntity scheduleEntity = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(scheduleEntity);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleEntity>> getAllSchedules() {
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }
}