package org.example.scheduler.repository;

import org.example.scheduler.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    int countByScheduleId(Long scheduleId); // 댓글 갯수 카운터
    List<Comment> findAllByScheduleIdOrderByCreatedAtDesc(Long scheduleId); // 일정 댓글
}