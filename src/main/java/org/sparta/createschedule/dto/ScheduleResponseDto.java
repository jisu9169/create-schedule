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

  private String title;
  private String description;
  private String manager;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;


  public static ScheduleResponseDto from(Schedule schedule) {
    return ScheduleResponseDto.builder()
        .title(schedule.getTitle())
        .description(schedule.getDescription())
        .manager(schedule.getManager())
        .createdAt(schedule.getCreateAt())
        .modifiedAt(schedule.getModifiedAt())
        .build();
  }

  public static ScheduleResponseDto from(ScheduleRequestDto scheduleRequestDto) {
    return ScheduleResponseDto.builder()
        .title(scheduleRequestDto.getTitle())
        .description(scheduleRequestDto.getDescription())
        .manager(scheduleRequestDto.getManager())
        .createdAt(scheduleRequestDto.getCreatedAt())
        .modifiedAt(scheduleRequestDto.getModifiedAt())
        .build();
  }
}
