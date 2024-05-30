package org.sparta.createschedule.repository;

import org.sparta.createschedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
