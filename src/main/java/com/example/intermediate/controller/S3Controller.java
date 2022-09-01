package com.example.intermediate.controller;


import com.example.intermediate.controller.response.ImageResponseDto;
import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.service.S3UploaderService;
import com.example.intermediate.util.ImageScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3UploaderService s3Uploader;
    private final ImageScheduler imageScheduler;

   
    //List<MultipartFile> files

    @PostMapping("/api/auth/image")
    public ResponseDto<?> imageUpload(@RequestParam("image") MultipartFile multipartFile){

        if(multipartFile.isEmpty()){
            return ResponseDto.fail("INVALID_FILE","파일이 유효하지 않습니다.");
        }
        try{
           return ResponseDto.success(new ImageResponseDto(s3Uploader.uploadFiles(multipartFile,"static")) );
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.fail("INVALID_FILE","파일이 유효하지 않습니다.");
        }

    }


    @GetMapping("/api/auth/test")
    public void test(){
        try{
            imageScheduler.deleteImage();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
