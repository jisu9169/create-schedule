package org.sparta.createschedule.repository;

import java.util.Optional;
import org.sparta.createschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
