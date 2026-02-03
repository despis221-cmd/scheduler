package org.example.scheduler.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String creator;
    private String password;
}
