package org.sparta.createschedule.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.common.CommonResponse;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

  private final ScheduleService scheduleService;

  @PostMapping("/create")
  @Operation(summary = "일정 생성", description = "일정 생성 API")
  public ResponseEntity<CommonResponse<ScheduleResponseDto>> CreateSchedule(
      @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(CommonResponse.<ScheduleResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("생성이 완료 되었습니다.")
        .data(scheduleService.save(scheduleRequestDto))
        .build());
  }

  @GetMapping("/{id}")
  @Operation(summary = "일정 단건 조회", description = "일정 단건 조회 API")
  public ResponseEntity<CommonResponse<ScheduleResponseDto>> getSchedule(@PathVariable Long id) {
    return ResponseEntity.ok().body(CommonResponse.<ScheduleResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("조회가 완료 되었습니다.")
        .data(scheduleService.getSchedule(id))
        .build());
  }

  @GetMapping
  @Operation(summary = "일정 모두 조회", description = "일정 모두 조회 API")
  public ResponseEntity<CommonResponse<List<ScheduleResponseDto>>> getAllSchedule() {
    return ResponseEntity.ok().body(CommonResponse.<List<ScheduleResponseDto>>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("전체 조회가 완료 되었습니다.")
        .data(scheduleService.getAllSchedule())
        .build());
  }

  @PatchMapping("/patch")
  @Operation(summary = "일정 수정", description = "일정 수정 API")
  public ResponseEntity<CommonResponse<ScheduleResponseDto>> UpdateSchedule(
      @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(CommonResponse.<ScheduleResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("수정이 완료 되었습니다.")
        .data(scheduleService.updateSchedule(scheduleRequestDto))
        .build());
  }

  @DeleteMapping("/delete")
  @Operation(summary = "일정 삭제", description = "일정 삭제 API")
  public ResponseEntity<CommonResponse<ScheduleResponseDto>> DeleteSchedule(
      @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(CommonResponse.<ScheduleResponseDto>builder()
        .statusCode(HttpStatus.OK.value())
        .msg("삭제가 완료 되었습니다.")
        .data(scheduleService.deleteSchedule(scheduleRequestDto))
        .build());
  }
}
