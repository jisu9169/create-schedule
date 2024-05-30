package org.sparta.createschedule.dto;

import lombok.Getter;
import org.sparta.createschedule.entity.User;
import org.sparta.createschedule.entity.UserRoleEnum;

@Getter
public class SignResponseDto {

  private Long id;
  private String nickname;
  private String username;
  private UserRoleEnum role;

  public SignResponseDto(User user) {
    this.id = user.getId();
    this.nickname = user.getUsername();
    this.username = user.getUsername();
    this.role = user.getRole();
  }
}
