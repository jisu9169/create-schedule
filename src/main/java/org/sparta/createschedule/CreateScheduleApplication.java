package org.sparta.createschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CreateScheduleApplication {

  public static void main(String[] args) {
    SpringApplication.run(CreateScheduleApplication.class, args);
  }

}
