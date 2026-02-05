package org.example.scheduler.check;

import org.example.scheduler.dto.CommentRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CommentCheck {
    private static final int MAX_CONTENT_LENGTH = 100;

    private static final String FIELD_CONTENT = "댓글 내용";
    private static final String FIELD_CREATOR = "작성자명";
    private static final String FIELD_PASSWORD = "비밀번호";

    public void checkCreate(CommentRequestDto dto) {
        validateRequired(dto.getContent(), FIELD_CONTENT);
        validateLength(dto.getContent(), MAX_CONTENT_LENGTH, FIELD_CONTENT);

        validateRequired(dto.getCreator(), FIELD_CREATOR);
        validateRequired(dto.getPassword(), FIELD_PASSWORD);
    }

    private void validateRequired(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다.");
        }
    }

    private void validateLength(String value, int maxLength, String fieldName) {
        if (value != null && value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + "은(는) " + maxLength + "자 이내여야 합니다.");
        }
    }
}
