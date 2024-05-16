package org.sparta.createschedule.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.entity.Schedule;
import org.sparta.createschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  // 스케줄 수정
  @Transactional
  public ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = findBySchedule(scheduleRequestDto.getId());
    if (Objects.equals(scheduleRequestDto.getPassword(), "") ||
        !Objects.equals(scheduleRequestDto.getPassword(), schedule.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
    }
    schedule.update(scheduleRequestDto);

    return ScheduleResponseDto.from(schedule);
  }

  //스케줄 삭제
  @Transactional
  public ScheduleResponseDto deleteSchedule(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = findBySchedule(scheduleRequestDto.getId());
    scheduleRepository.delete(schedule);
    return ScheduleResponseDto.from(scheduleRequestDto);
  }


  // 스케줄 조회
  private Schedule findBySchedule(Long id) {
    return scheduleRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
  }
}
