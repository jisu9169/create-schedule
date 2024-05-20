package org.sparta.createschedule.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.sparta.createschedule.dto.ScheduleRequestDto;
import org.sparta.createschedule.dto.ScheduleResponseDto;
import org.sparta.createschedule.entity.Schedule;
import org.sparta.createschedule.exception.ErrorStatus;
import org.sparta.createschedule.exception.ScheduleException;
import org.sparta.createschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  // 스케줄 생성
  public Schedule save(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = new Schedule();
    return scheduleRepository.save(schedule.save(scheduleRequestDto));
  }

  // 스케줄 조회
  public Schedule getSchedule(Long id) {
    return findBySchedule(id);
  }

  //스케줄 전체 조회
  public List<Schedule> getAllSchedule() {
    return scheduleRepository.findAllByOrderByModifiedAtDesc();
  }

  // 스케줄 수정
  @Transactional
  public Schedule updateSchedule(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = validatePassword(scheduleRequestDto);
    schedule.update(scheduleRequestDto);
    return schedule;
  }

  //스케줄 삭제
  @Transactional
  public Schedule deleteSchedule(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = validatePassword(scheduleRequestDto);
    scheduleRepository.delete(schedule);
    return schedule;
  }

  // 비밀번호 검증
  private Schedule validatePassword(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = findBySchedule(scheduleRequestDto.getId());
    if (Objects.equals(scheduleRequestDto.getPassword(), "")) {
      throw new ScheduleException(ErrorStatus.IS_NOT_PASSWORD);
    }

    if (!Objects.equals(scheduleRequestDto.getPassword(), schedule.getPassword())) {
      throw new ScheduleException(ErrorStatus.IS_NOT_PASSWORD);
    }

    return schedule;
  }

  // 스케줄 조회
  private Schedule findBySchedule(Long id) {
    return scheduleRepository.findById(id)
        .orElseThrow(() -> new ScheduleException(ErrorStatus.ID_NOT_FOUND));
  }
}
