package org.example.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 API
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {

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

    // 일정 수정 API
    @PatchMapping("/schedules/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    // 일정 삭제 API
    @DeleteMapping("/schedules/{id}")
    public String deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        String title = scheduleService.deleteSchedule(id, requestDto.getPassword());
        return title + "이(가) 삭제되었습니다.";
    }
}
