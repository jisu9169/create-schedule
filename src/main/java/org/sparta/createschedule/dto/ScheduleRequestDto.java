package org.sparta.createschedule.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleRequestDto {

  private Long id;
  @NotEmpty(message = "할일 제목은 필수값입니다.")
  @Size(max = 200, message = "할일 제목은 200자 이내로 입력해주세요.")
  private String title;
  private String description;
  @Email(message = "올바른 이메일 형식이 아닙니다.")
  private String manager;
  @NotEmpty(message = "비밀번호는 필수값입니다.")
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
