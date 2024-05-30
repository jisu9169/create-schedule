package org.sparta.createschedule.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.SignRequestDto;
import org.sparta.createschedule.dto.SignResponseDto;
import org.sparta.createschedule.entity.User;
import org.sparta.createschedule.entity.UserRoleEnum;
import org.sparta.createschedule.exception.ErrorStatus;
import org.sparta.createschedule.exception.ScheduleException;
import org.sparta.createschedule.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

  public SignResponseDto signup(SignRequestDto requestDto) {
    String username = requestDto.getUsername();

    Optional<User> checkUsername = userRepository.findByUsername(username);
    if (checkUsername.isPresent()) {
      throw new ScheduleException(ErrorStatus.DUPLICATE_NAME);
    }

    UserRoleEnum role = UserRoleEnum.USER;
    if (requestDto.isAdmin()) {
      if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
        throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
      }
      role = UserRoleEnum.ADMIN;
    }

    User user = userRepository.save(new User(requestDto, role));

    return new SignResponseDto(user);

  }
}
