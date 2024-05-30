package org.sparta.createschedule.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.sparta.createschedule.entity.Schedule;

@Getter
@AllArgsConstructor
@Builder
public class ScheduleResponseDto {

  private Long id;
  private String title;
  private String description;
  private String manager;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public ScheduleResponseDto(Schedule schedule) {
    this.id = schedule.getId();
    this.title = schedule.getTitle();
    this.description = schedule.getDescription();
    this.manager = schedule.getManager();
    this.createdAt =  schedule.getCreateAt();
    this.modifiedAt =  schedule.getModifiedAt();
  }
}
