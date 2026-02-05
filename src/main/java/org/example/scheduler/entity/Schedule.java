package org.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduler.dto.ScheduleRequestDto;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private String password;


    // 요청 DTO 받아 엔티티 생성
    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.creator = requestDto.getCreator();
        this.password = requestDto.getPassword();
    }

    // 일정 제목, 작성자명 수정
    public void update(String title, String creator) {
        this.title = title;
        this.creator = creator;
    }
}
