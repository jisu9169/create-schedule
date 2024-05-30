package org.sparta.createschedule.dto;

import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
  private Long commentId;
  private String comment;
}
