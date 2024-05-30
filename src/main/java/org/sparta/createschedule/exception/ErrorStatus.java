package org.sparta.createschedule.exception;


import lombok.Getter;

@Getter
public enum ErrorStatus {

  ID_NOT_FOUND("아이디가 존재하지 않습니다.", 400),
  IS_NOT_PASSWORD("비밀번호가 일치하지 않습니다.", 400),

  FILE_NOT_FOUND("파일을 찾을 수 없습니다", 404),
  FILE_NOT_IMAGE("이미지 파일이 아닙니다", 400),
  IMAGE_SAVE_FAIL("이미지 저장하는데 실패했습니다.", 400),

  SCHEDULE_NOT_FOUND("스케줄 정보를 찾을 수가 없습니다." , 400),

  COMMENT_NOT_FOUND("댓글 내용이 없습니다" , 400);

  private final String errorMessage;
  private final int status;

  ErrorStatus(String errorMessage, int status) {
    this.errorMessage = errorMessage;
    this.status = status;
  }
}
