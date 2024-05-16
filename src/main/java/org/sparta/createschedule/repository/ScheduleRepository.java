package org.sparta.createschedule.repository;

import java.util.List;
import org.sparta.createschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  List<Schedule> findAllByOrderByModifiedAtDesc();

}
