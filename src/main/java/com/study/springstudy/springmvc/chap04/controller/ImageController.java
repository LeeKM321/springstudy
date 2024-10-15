package com.study.springstudy.springmvc.chap04.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/display")
@Slf4j
public class ImageController {

    @Value("${file.upload.root-path}")
    private String rootPath;

    /*
     # img 태그의 src속성을 통해서 들어오는 요청을 처리.
     페이지가 렌더링 될 때, img에 작성된 요청 url을 통해 비동기 방식의 요청이 들어옵니다.
     /display/2024/10/15/844cde531b9d41a7937c8a2ef246c39c.png
     */
    @GetMapping("/{y}/{m}/{d}/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String y,
                                      @PathVariable String m,
                                      @PathVariable String d,
                                      @PathVariable String fileName) {

        // 파일의 전체 경로를 생성 (rootPath까지 합쳐서)
        String fullPath = String.format("%s/%s/%s/%s/%s", rootPath, y, m, d, fileName);
        log.info("fullPath: {}", fullPath);

        File file = new File(fullPath);
        ResponseEntity<byte[]> result = null;
        HttpHeaders headers = new HttpHeaders(); // 응답용 헤더 객체 생성.

        try {
            // probeContentType: 매개값으로 전달받은 파일의 컨텐트 타입이 무엇인지를 문자열로 반환.
            headers.add("Content-type", Files.probeContentType(file.toPath()));


            result  // 전달하고자 하는 파일을 카피한 후 바이트배열로 변환해서 전달.
                    = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return result;
    }


}















