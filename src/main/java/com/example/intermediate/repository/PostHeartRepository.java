package com.example.intermediate.repository;

import com.example.intermediate.domain.Heart;

import java.util.List;
import java.util.Optional;

import com.example.intermediate.domain.PostHeart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostHeartRepository extends JpaRepository< PostHeart, Long> {
  Optional< PostHeart> findByRequestIdAndNickname(Long PostId, String Nickname);
  List< PostHeart> findAllByRequestId(Long RequestId);

  List<PostHeart> findAllByNickname(String Nickname);
}
