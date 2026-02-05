package org.example.scheduler.check;

import org.example.scheduler.dto.CommentRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CommentCheck {
    public void checkCreate(CommentRequestDto dto) {
        if (dto.getContent() == null || dto.getContent().isBlank()) {
            throw new IllegalArgumentException("댓글 내용은 필수입니다.");
        }
        if (dto.getContent().length() > 100) {
            throw new IllegalArgumentException("댓글 내용은 100자 이내여야 합니다.");
        }
        if (dto.getCreator() == null || dto.getCreator().isBlank()) {
            throw new IllegalArgumentException("작성자명은 필수입니다.");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }
}
