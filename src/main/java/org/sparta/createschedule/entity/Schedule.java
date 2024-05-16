package org.sparta.createschedule.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@Builder
public class Schedule {

  @Id
  private Long id;
  private String title;
  private String description;
  private String manager;
  private String password;

}
