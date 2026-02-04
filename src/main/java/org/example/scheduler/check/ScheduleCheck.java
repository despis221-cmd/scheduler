package org.example.scheduler.check;

import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleUpdateDto;

public class ScheduleCheck {
    public void checkCreate(ScheduleRequestDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        if (dto.getTitle().length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이내여야 합니다.");
        }
        if (dto.getContent() == null || dto.getContent().isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }
        if (dto.getContent().length() > 200) {
            throw new IllegalArgumentException("내용은 200자 이내여야 합니다.");
        }
        if (dto.getCreator() == null || dto.getCreator().isBlank()) {
            throw new IllegalArgumentException("작성자명은 필수입니다.");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }

    public void checkUpdate(ScheduleUpdateDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        if (dto.getTitle().length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이내여야 합니다.");
        }
        if (dto.getCreator() == null || dto.getCreator().isBlank()) {
            throw new IllegalArgumentException("작성자명은 필수입니다.");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }

    public void checkPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }
}
