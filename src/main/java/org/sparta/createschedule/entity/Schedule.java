package org.sparta.createschedule.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;

@Entity
@Getter
@RequiredArgsConstructor
@Builder
public class Schedule extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false, length = 500)
  private String description;
  @Column(name = "manager", nullable = false)
  private String manager;
  @Column(name = "password", nullable = false)
  private String password;

  public void update(ScheduleRequestDto scheduleRequestDto) {
    this.title = scheduleRequestDto.getTitle();
    this.description = scheduleRequestDto.getDescription();
    this.manager = scheduleRequestDto.getManager();
  }
}
