package org.sparta.createschedule.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> scheduleExceptionHandler(ScheduleException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
  }
}
