package org.example.scheduler.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduler.check.CommentCheck;
import org.example.scheduler.dto.CommentRequestDto;
import org.example.scheduler.dto.CommentResponseDto;
import org.example.scheduler.entity.Comment;
import org.example.scheduler.repository.CommentRepository;
import org.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository; // 일정 확인
    private final CommentCheck commentCheck;

    // 댓글 생성 요청 처리
    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        commentCheck.checkCreate(requestDto);
        scheduleRepository.findById(scheduleId).orElseThrow( // ID 확인
                () -> new IllegalArgumentException("선택한 일정이 존재하지 않습니다.")
        );

        int commentCount = commentRepository.countByScheduleId(scheduleId);
        if (commentCount >= 10) { // 갯수 확인
            throw new IllegalArgumentException("댓글은 한 일정당 10개까지만 작성할 수 있습니다.");
        }

        Comment comment = new Comment(requestDto, scheduleId);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }
}