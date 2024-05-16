package org.sparta.createschedule.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.entity.Schedule;
import org.sparta.createschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  // 스케줄 생성
  public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
    Schedule saveSchedule = scheduleRepository.save(Schedule.builder()
        .title(scheduleRequestDto.getTitle())
        .description(scheduleRequestDto.getDescription())
        .manager(scheduleRequestDto.getManager())
        .password(scheduleRequestDto.getPassword())
        .build());

    return ScheduleResponseDto.from(saveSchedule);
  }

  //스케줄 전체 조회
  public List<ScheduleResponseDto> getAllSchedule() {
    return scheduleRepository.findAllByOrderByModifiedAtDesc().stream()
        .map(ScheduleResponseDto::from).toList();
  }


}
