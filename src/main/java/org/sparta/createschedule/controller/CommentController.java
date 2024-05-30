package org.sparta.createschedule.controller;

import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.common.CommonResponse;
import org.sparta.createschedule.dto.CommentRequestDto;
import org.sparta.createschedule.dto.CommentResponseDto;
import org.sparta.createschedule.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class CommentController {

  private final CommentService commentService;


  @PostMapping("/{scheduleId}/comments")
  public ResponseEntity<CommonResponse<CommentResponseDto>> addComment(
      @PathVariable long scheduleId, @RequestBody CommentRequestDto requestDto) {
    CommentResponseDto responseDto = commentService.addComment(scheduleId, requestDto);

    return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("댓글 등록 완료")
        .data(responseDto)
        .build());
  }
}
