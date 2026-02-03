package org.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 30, message = "제목은 30자 이내여야 합니다.")
    private String title;
    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 200, message = "내용은 200자 이내여야 합니다.")
    private String content;
    @NotBlank(message = "작성자명은 필수입니다.")
    private String creator;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
