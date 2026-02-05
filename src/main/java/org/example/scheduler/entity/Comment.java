package org.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduler.dto.CommentRequestDto;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long scheduleId;

    public Comment(CommentRequestDto requestDto, Long scheduleId) {
        this.content = requestDto.getContent();
        this.creator = requestDto.getCreator();
        this.password = requestDto.getPassword();
        this.scheduleId = scheduleId;
    }
}
