package org.example.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.check.ScheduleCheck;
import org.example.scheduler.dto.CommentResponseDto;
import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.dto.ScheduleUpdateDto;
import org.example.scheduler.entity.Comment;
import org.example.scheduler.entity.Schedule;
import org.example.scheduler.repository.CommentRepository;
import org.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final ScheduleCheck scheduleCheck;

    // 일정 생성 요청 처리
    @Transactional
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        scheduleCheck.checkCreate(requestDto);
        Schedule schedule = new Schedule(requestDto); // 엔티티 객체 생성
        Schedule savedSchedule = scheduleRepository.save(schedule); // DB에 엔티티 저장
        return new ScheduleResponseDto(savedSchedule); // 저장된 엔티티 DTO로 변환하여 반환
    }

    // 전체 일정 혹은 특정 작성자 전체 일정 조회 요청 처리
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules(String creator) {
        List<Schedule> scheduleList;
        if (creator == null) {
            scheduleList = scheduleRepository.findAllByOrderByModifiedAtDesc();
        } else {
            scheduleList = scheduleRepository.findAllByCreatorOrderByModifiedAtDesc(creator);
        }
        return scheduleList.stream().map(ScheduleResponseDto::new).toList();
    }

    // 선택 일정 조회 요청 처리
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findScheduleById(id);
        List<CommentResponseDto> commentDtoList = commentRepository.findAllByScheduleIdOrderByCreatedAtDesc(id)
                .stream()
                .map(CommentResponseDto::new)
                .toList();
        return new ScheduleResponseDto(schedule, commentDtoList);
    }

    // 일정 수정 요청 처리
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateDto updateDto, String password) {
        scheduleCheck.checkUpdate(updateDto);
        Schedule schedule = findScheduleById(id);

        validatePassword(schedule, password);

        schedule.update(updateDto.getTitle(), updateDto.getCreator());
        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제 요청 처리
    @Transactional
    public String deleteSchedule(Long id, String password) {
        scheduleCheck.checkPassword(password);
        Schedule schedule = findScheduleById(id);

        validatePassword(schedule, password);

        String title = schedule.getTitle();
        scheduleRepository.delete(schedule);
        return title;
    }

    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );
    }

    private void validatePassword(Schedule schedule, String inputPassword) {
        if (!schedule.getPassword().equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
