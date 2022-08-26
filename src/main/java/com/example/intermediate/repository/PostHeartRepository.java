package com.example.intermediate.repository;

import com.example.intermediate.domain.Heart;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostHeartRepository extends JpaRepository<Heart, Long> {
  Optional<Heart> findByRequestIdAndNickname(Long PostId, String Nickname);
  List<Heart> findAllByRequestId(Long RequestId);
}
