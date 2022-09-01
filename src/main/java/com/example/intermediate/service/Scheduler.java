package com.example.intermediate.service;

import com.example.intermediate.domain.Comment;
import com.example.intermediate.domain.Post;
import com.example.intermediate.repository.CommentRepository;
import com.example.intermediate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성합니다.
@Component // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가합니다.
public class Scheduler {

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(Scheduler.class);
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final Logger logger = LoggerFactory.getLogger("게시물 삭제 로그");





    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void Dletele(){
        System.out.println("게시글 삭제");

        List<Post> postList = postRepository.findAll();
        for (Post post2 : postList ) {
          List <Comment> comment = commentRepository.findAllByPost(post2);
          if(comment.isEmpty()) {
              postRepository.delete(post2);
              logger.info(post2.getId()+"번째 게시물 삭제");
          }
        }
    }
}