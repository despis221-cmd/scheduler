package org.example.scheduler.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
    private String creator;
    private String password;
}
