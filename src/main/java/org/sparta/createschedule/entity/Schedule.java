package org.sparta.createschedule.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped {

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

  @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> commentList = new ArrayList<>();

  public Schedule(ScheduleRequestDto scheduleRequestDto) {
    this.title = scheduleRequestDto.getTitle();
    this.description = scheduleRequestDto.getDescription();
    this.manager = scheduleRequestDto.getManager();
    this.password = scheduleRequestDto.getPassword();
  }

}
