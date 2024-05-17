package org.sparta.createschedule.controller;


import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

  private final ScheduleService scheduleService;

  @PostMapping("/create")
  @Operation(summary = "일정 생성", description = "일정 생성 API")
  public ResponseEntity<ScheduleResponseDto> CreateSchedule(
      @RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(scheduleService.save(scheduleRequestDto));
  }

  @GetMapping("/get/{id}")
  @Operation(summary = "일정 단건 조회", description = "일정 단건 조회 API")
  public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id){
    return ResponseEntity.ok().body(scheduleService.getSchedule(id));
  }

  @GetMapping("/getall")
  @Operation(summary = "일정 모두 조회", description = "일정 모두 조회 API")
  public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule() {
    return ResponseEntity.ok().body(scheduleService.getAllSchedule());
  }

  @PatchMapping("/patch")
  @Operation(summary = "일정 수정", description = "일정 수정 API")
  public ResponseEntity<ScheduleResponseDto> UpdateSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(scheduleService.updateSchedule(scheduleRequestDto));
  }

  @DeleteMapping("/delete")
  @Operation(summary = "일정 삭제", description = "일정 삭제 API")
  public ResponseEntity<ScheduleResponseDto> DeleteSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(scheduleService.deleteSchedule(scheduleRequestDto));
  }
}
