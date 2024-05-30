package org.sparta.createschedule.repository;

import java.util.Optional;
import org.sparta.createschedule.entity.Comment;
import org.sparta.createschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> findByIdAndSchedule(Long commentId, Schedule schedule);
}
