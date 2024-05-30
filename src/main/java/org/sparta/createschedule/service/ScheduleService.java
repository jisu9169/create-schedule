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
  public ScheduleResponseDto save(ScheduleRequestDto requestDto) {
    Schedule schedule = scheduleRepository.save(new Schedule(requestDto));
    return new ScheduleResponseDto(schedule);
  }

  // 스케줄 조회
  public ScheduleResponseDto getSchedule(Long id) {
    return new ScheduleResponseDto(findBySchedule(id));
  }

  //스케줄 전체 조회
  public List<ScheduleResponseDto> getAllSchedule() {
    return scheduleRepository.findAllByOrderByModifiedAtDesc().stream()
        .map(ScheduleResponseDto::new).toList();
  }

  // 스케줄 수정
  @Transactional
  public ScheduleResponseDto updateSchedule(ScheduleRequestDto requestDto) {
    if (!validatePassword(requestDto)) {
      throw new ScheduleException(ErrorStatus.IS_NOT_PASSWORD);
    }

    Schedule schedules = scheduleRepository.save(new Schedule(requestDto));
    return new ScheduleResponseDto(schedules);
  }

  //스케줄 삭제
  @Transactional
  public void deleteSchedule(ScheduleRequestDto requestDto) {
    if (!validatePassword(requestDto)) {
      throw new ScheduleException(ErrorStatus.IS_NOT_PASSWORD);
    }
    scheduleRepository.delete(new Schedule(requestDto));
  }

  // 비밀번호 검증
  private boolean validatePassword(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = findBySchedule(scheduleRequestDto.getId());
    if (Objects.equals(scheduleRequestDto.getPassword(), "")) {
      return false;
    }
    if (!Objects.equals(scheduleRequestDto.getPassword(), schedule.getPassword())) {
      return false;
    }
    return true;
  }

  // 스케줄 조회
  private Schedule findBySchedule(Long id) {
    return scheduleRepository.findById(id)
        .orElseThrow(() -> new ScheduleException(ErrorStatus.ID_NOT_FOUND));
  }
}
