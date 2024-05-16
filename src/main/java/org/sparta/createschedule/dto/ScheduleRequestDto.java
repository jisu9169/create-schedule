package org.sparta.createschedule.dto;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleRequestDto {

  private Long id;
  private String title;
  private String description;
  private String manager;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
