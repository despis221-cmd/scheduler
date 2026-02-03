package org.example.scheduler.service;

import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // Repository 의존성 주입
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 생성 요청 처리
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto); // 엔티티 객체 생성
        Schedule savedSchedule = scheduleRepository.save(schedule); // DB에 엔티티 저장
        return new ScheduleResponseDto(savedSchedule); // 저장된 엔티티 DTO로 변환하여 반환
    }
}
