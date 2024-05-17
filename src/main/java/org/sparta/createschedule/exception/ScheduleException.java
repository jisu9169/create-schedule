package org.sparta.createschedule.exception;

import lombok.Getter;

@Getter
public class ScheduleException extends IllegalArgumentException {

  private final int status;

  public ScheduleException(ErrorStatus errorStatus) {
    super(errorStatus.getErrorMessage());
    this.status = errorStatus.getStatus();
  }
}
