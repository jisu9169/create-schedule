package org.sparta.createschedule.service;


import java.util.Objects;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final ScheduleService scheduleService;

  public CommentResponseDto addComment(long scheduleId, User user,
      CommentRequestDto requestDto) {
    Schedule schedule = scheduleService.findBySchedule(scheduleId);
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
  public CommentResponseDto updateComment(Long scheduleId, User user, Long commentId,
      CommentUpdateRequestDto requestDto) {
    Schedule schedule = scheduleService.findBySchedule(scheduleId);
    Comment comment = CommentValidation(commentId, user, schedule);
    comment.update(requestDto);

    return new CommentResponseDto(schedule.getId(), comment);
  }

  public void deleteComment(Long scheduleId,User user, Long commentId) {
    Schedule schedule = scheduleService.findBySchedule(scheduleId);
    commentRepository.findByIdAndSchedule(commentId, schedule).orElseThrow( () ->new ScheduleException(ErrorStatus.COMMENT_NOT_FOUND));
    Comment comment = CommentValidation(commentId, user, schedule);

    commentRepository.delete(comment);
  }

  private Comment CommentValidation(Long commentId, User user, Schedule schedule) {
    return commentRepository.findByIdAndUserAndSchedule(commentId, user,schedule).orElseThrow (() -> new ScheduleException(ErrorStatus.COMMENT_NOT_FOUND));
  }
}
