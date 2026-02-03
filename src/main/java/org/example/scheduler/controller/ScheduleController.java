package org.example.scheduler.controller;

import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    // ScheduleService 의존성 주입
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성 API
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.saveSchedule(requestDto);
    }

    // 전체 일정 조회 API
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String creator) {
        return scheduleService.getSchedules(creator);
    }

    // 선택 일정 조회 API
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }
}
