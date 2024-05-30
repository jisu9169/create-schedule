package org.sparta.createschedule.controller;

import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.common.CommonResponse;
import org.sparta.createschedule.dto.CommentRequestDto;
import org.sparta.createschedule.dto.CommentResponseDto;
import org.sparta.createschedule.dto.CommentUpdateRequestDto;
import org.sparta.createschedule.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/comments")
  public ResponseEntity<CommonResponse<CommentResponseDto>> addComment(
      @PathVariable Long scheduleId, @RequestBody CommentRequestDto requestDto) {
    CommentResponseDto responseDto = commentService.addComment(scheduleId, requestDto);

    return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("댓글 등록 완료")
        .data(responseDto)
        .build());
  }

  @PatchMapping("/comments/patch")
  public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(
      @PathVariable Long scheduleId, @RequestBody CommentUpdateRequestDto requestDto) {
    CommentResponseDto responseDto = commentService.updateComment(scheduleId, requestDto);

    return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("댓글 수정 완료!!")
        .data(responseDto)
        .build());
  }

  @DeleteMapping("/comments/{commentId}/delete")
  public ResponseEntity<CommonResponse<CommentResponseDto>> deleteComment(
      @PathVariable Long scheduleId, @PathVariable Long commentId) {
    commentService.deleteComment(scheduleId, commentId);
    return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("댓글 삭제 완료!!")
        .build());
  }

}
