package org.sparta.createschedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.createschedule.dto.CommentRequestDto;
import org.sparta.createschedule.dto.CommentUpdateRequestDto;


@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String comment;
  private String userName;
  @ManyToOne
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  public Comment(Schedule schedule, CommentRequestDto requestDto) {
    this.schedule = schedule;
    this.comment = requestDto.getComment();
    this.userName = requestDto.getUserName();
  }

  public void  update(CommentUpdateRequestDto requestDto) {
    this.comment = requestDto.getComment();
  }
}
