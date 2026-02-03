package org.example.scheduler.repository;

import org.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc(); // 수정일 기준 내림차순
    List<Schedule> findAllByCreatorOrderByModifiedAtDesc(String creator); // 작성자명
}