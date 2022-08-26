package com.example.intermediate.repository;

import com.example.intermediate.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentHeartRepository extends JpaRepository<Heart, Long> {
  Optional<Heart> findByidAndNickname(Long CommentId, String Nickname);
}
