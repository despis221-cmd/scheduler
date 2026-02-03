package org.example.scheduler.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import org.example.scheduler.entity.Schedule;


@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String creator;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 엔티티 받아 응답 DTO로 변환 (비밀번호 제외)
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.creator = schedule.getCreator();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
