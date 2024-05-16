package org.sparta.createschedule.controller;


import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

  private final ScheduleService scheduleService;

  @PostMapping("/create")
  public ResponseEntity<ScheduleResponseDto> CreateSchedule(
      @RequestBody ScheduleRequestDto scheduleRequestDto) {
    return ResponseEntity.ok().body(scheduleService.save(scheduleRequestDto));
  }
}
