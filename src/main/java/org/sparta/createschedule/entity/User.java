package org.sparta.createschedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.createschedule.dto.SignRequestDto;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nickName;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;

  @OneToMany
  private List<Comment> commentList = new ArrayList<>();

  public User(SignRequestDto requestDto, UserRoleEnum role) {
    this.nickName = requestDto.getNickname();
    this.username = requestDto.getUsername();
    this.password = requestDto.getPassword();
    this.role = role;

  }
}
