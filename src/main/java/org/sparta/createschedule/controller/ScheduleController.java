package org.sparta.createschedule.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @GetMapping("/getall")
  public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule() {
    return ResponseEntity.ok().body(scheduleService.getAllSchedule());
  }


}
