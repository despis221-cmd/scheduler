package org.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 100, message = "댓글 내용은 100자 이내여야 합니다.")
    private String content;
    @NotBlank(message = "작성자명은 필수입니다.")
    private String creator;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
