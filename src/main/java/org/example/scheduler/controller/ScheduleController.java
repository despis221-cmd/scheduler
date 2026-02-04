package org.example.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.dto.ScheduleUpdateDto;
import org.example.scheduler.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 API
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.saveSchedule(requestDto);
    }

    // 전체 일정 조회 API
    @GetMapping
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String creator) {
        return scheduleService.getSchedules(creator);
    }

    // 선택 일정 조회 API
    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    // 일정 수정 API
    @PatchMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateDto updateDto) {
        return scheduleService.updateSchedule(scheduleId, updateDto);
    }

    // 일정 삭제 API
    @DeleteMapping("/{scheduleId}")
    public String deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        String title = scheduleService.deleteSchedule(scheduleId, requestDto.getPassword());
        return title + "이(가) 삭제되었습니다.";
    }
}
