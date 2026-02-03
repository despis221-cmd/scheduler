package org.example.scheduler.dto;

import lombok.Getter;
import org.example.scheduler.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String creator;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long scheduleId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.creator = comment.getCreator();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.scheduleId = comment.getScheduleId();
    }
}
