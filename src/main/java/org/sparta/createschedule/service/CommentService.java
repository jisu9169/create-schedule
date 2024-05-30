package org.sparta.createschedule.service;


import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.CommentRequestDto;
import org.sparta.createschedule.dto.CommentResponseDto;
import org.sparta.createschedule.dto.CommentUpdateRequestDto;
import org.sparta.createschedule.entity.Comment;
import org.sparta.createschedule.entity.Schedule;
import org.sparta.createschedule.entity.User;
import org.sparta.createschedule.exception.ErrorStatus;
import org.sparta.createschedule.exception.ScheduleException;
import org.sparta.createschedule.repository.CommentRepository;
import org.sparta.createschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final ScheduleRepository scheduleRepository;

  public CommentResponseDto addComment(long scheduleId, User user,
      CommentRequestDto requestDto) {
    Schedule schedule = ScheduleValidation(scheduleId);
    if (Objects.equals(requestDto.getComment(), "")) {
      throw new ScheduleException(ErrorStatus.COMMENT_NOT_FOUND);
    }
    if (Objects.equals(requestDto.getComment(), null)) {
      throw new ScheduleException(ErrorStatus.COMMENT_NOT_FOUND);
    }

    Comment comment = commentRepository.save(new Comment(schedule, user, requestDto));

    return new CommentResponseDto(scheduleId, comment);

  }

  @Transactional
  public CommentResponseDto updateComment(Long scheduleId, User user, CommentUpdateRequestDto requestDto) {
    Schedule schedule = ScheduleValidation(scheduleId);
    Comment comment = CommentValidation(requestDto.getCommentId(), user, schedule);
    comment.update(requestDto);

    return new CommentResponseDto(schedule.getId(), comment);
  }

  public void deleteComment(Long scheduleId,User user, Long commentId) {
    Schedule schedule = ScheduleValidation(scheduleId);
    Optional<Comment> isComment = commentRepository.findByIdAndSchedule(commentId, schedule);
    Comment comment = CommentValidation(commentId, user, schedule);
    if (isComment.isEmpty()) {
      throw new ScheduleException(ErrorStatus.COMMENT_NOT_FOUND);
    }
    commentRepository.delete(comment);
  }

  private Comment CommentValidation(Long commentId, User user, Schedule schedule) {
    Optional<Comment> isComment = commentRepository.findByIdAndUserAndSchedule(commentId, user,schedule);
    if (isComment.isEmpty()) {
      throw new ScheduleException(ErrorStatus.COMMENT_NOT_FOUND);
    }
    return isComment.get();
  }

  private Schedule ScheduleValidation(long scheduleId) {
    return scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
  }
}
