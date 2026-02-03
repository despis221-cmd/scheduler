package org.example.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.CommentRequestDto;
import org.example.scheduler.dto.CommentResponseDto;
import org.example.scheduler.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules") // 공통 주소
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성 API
    @PostMapping("/{scheduleId}/comments")
    public CommentResponseDto createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(scheduleId, requestDto);
    }
}
