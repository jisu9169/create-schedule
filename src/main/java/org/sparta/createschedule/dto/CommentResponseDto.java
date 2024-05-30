package org.sparta.createschedule.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.sparta.createschedule.entity.Comment;

@Getter
public class CommentResponseDto {

  private final Long CommentId;
  private final String comment;
  private final String userName;
  private final Long scheduleId;
  private final LocalDateTime createdAt;
  private final LocalDateTime modifiedAt;

  public CommentResponseDto(Long scheduleId, Comment comment) {
    this.CommentId = comment.getId();
    this.comment = comment.getComment();
    this.userName = comment.getUserName();
    this.scheduleId = scheduleId;
    this.createdAt = comment.getCreateAt();
    this.modifiedAt = comment.getModifiedAt();
  }
}
