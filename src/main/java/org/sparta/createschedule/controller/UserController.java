package org.sparta.createschedule.controller;


import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sparta.createschedule.common.CommonResponse;
import org.sparta.createschedule.dto.SignRequestDto;
import org.sparta.createschedule.dto.SignResponseDto;
import org.sparta.createschedule.exception.ErrorStatus;
import org.sparta.createschedule.exception.ScheduleException;
import org.sparta.createschedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserService userService;


  @PostMapping("/user/signup")
  public ResponseEntity<CommonResponse<SignResponseDto>> signup(
      @Valid @RequestBody SignRequestDto requestDto, BindingResult bindingResult) {
    SignResponseDto responseDto = userService.signup(requestDto);

    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    if (!fieldErrors.isEmpty()) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
      }
      throw new ScheduleException(ErrorStatus.VALIDATION_ERROR);
    }

    return ResponseEntity.ok().body(CommonResponse.<SignResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("회원 가입 완료")
        .data(responseDto)
        .build());
  }
}
