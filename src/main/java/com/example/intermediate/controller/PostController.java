package com.example.intermediate.controller;

import com.example.intermediate.controller.request.PostRequestDto;
import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.service.PostService;
import javax.servlet.http.HttpServletRequest;

import com.example.intermediate.service.S3UploaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class PostController {
  private final S3UploaderService s3Uploader;
  private final PostService postService;

//  @RequestMapping(value = "/api/auth/post", method = RequestMethod.POST)
//  public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto,
//      HttpServletRequest request) {
//    return postService.createPost(requestDto, request);
//  }

  @RequestMapping(value = "/api/auth/post", method = RequestMethod.POST)
  public ResponseDto<?> createPost(@RequestPart(value = "post") PostRequestDto requestDto,
                                   @RequestPart(value = "file") MultipartFile file ,
                                   HttpServletRequest request) {
    String imgUrl = "";
    if(file.isEmpty()){
      return ResponseDto.fail("INVALID_FILE","파일이 유효하지 않습니다.");
    }
    try{
      imgUrl = s3Uploader.uploadFiles(file,"static/");
    }catch (Exception e){
      e.printStackTrace();
      return ResponseDto.fail("INVALID_FILE","파일이 유효하지 않습니다.");
    }

    return postService.createPost(requestDto,imgUrl, request);
  }

  @RequestMapping(value = "/api/post/{id}", method = RequestMethod.GET)
  public ResponseDto<?> getPost(@PathVariable Long id) {
    return postService.getPost(id);
  }

  @RequestMapping(value = "/api/post", method = RequestMethod.GET)
  public ResponseDto<?> getAllPosts() {
    return postService.getAllPost();
  }

  @RequestMapping(value = "/api/auth/post/{id}", method = RequestMethod.PUT)
  public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto,
      HttpServletRequest request) {
    return postService.updatePost(id, postRequestDto, request);
  }

  @RequestMapping(value = "/api/auth/post/{id}", method = RequestMethod.DELETE)
  public ResponseDto<?> deletePost(@PathVariable Long id,
      HttpServletRequest request) {
    return postService.deletePost(id, request);
  }

  @RequestMapping(value = "/api/auth/post/{id}", method = RequestMethod.POST)
  public ResponseDto<?> likePost(@PathVariable Long id, HttpServletRequest request) {
    return postService.likePost(id, request);
  }
}
