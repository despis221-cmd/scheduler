package org.example.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.dto.ScheduleUpdateDto;
import org.example.scheduler.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 전체 일정 조회 API
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@RequestParam(required = false) String creator) {
        return ResponseEntity.ok(scheduleService.getSchedules(creator));
    }

    // 선택 일정 조회 API
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    // 일정 수정 API
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateDto updateDto, @RequestHeader("X-Schedule-Password") String password) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, updateDto, password));
    }

    // 일정 삭제 API
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        String title = scheduleService.deleteSchedule(scheduleId, requestDto.getPassword());
        return ResponseEntity.ok(title + "이(가) 삭제되었습니다.");
    }
}
