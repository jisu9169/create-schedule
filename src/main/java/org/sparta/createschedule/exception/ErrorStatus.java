package org.sparta.createschedule.exception;


import lombok.Getter;

@Getter
public enum ErrorStatus {

  ID_NOT_FOUND("아이디가 존재하지 않습니다.", 400),
  IS_NOT_PASSWORD("비밀번호가 일치하지 않습니다.", 400);

  private final String errorMessage;
  private final int status;

  ErrorStatus(String errorMessage, int status) {
    this.errorMessage = errorMessage;
    this.status = status;
  }
}
