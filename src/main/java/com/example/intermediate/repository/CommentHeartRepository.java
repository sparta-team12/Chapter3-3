package com.example.intermediate.repository;

import com.example.intermediate.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentHeartRepository extends JpaRepository<Heart, Long> {
  Optional<Heart> findByRequestIdAndNickname(Long CommentId, String Nickname);
  List<Heart> findAllByRequestId(Long RequestId);
}