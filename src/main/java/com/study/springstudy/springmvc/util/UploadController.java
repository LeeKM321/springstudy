package com.study.springstudy.springmvc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {

    // properties 파일에 작성한 값을 가져오는 아노테이션
    @Value("${file.upload.root-path}")
    private String rootPath;

    @GetMapping("/upload-form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    @PostMapping("/upload-file")
    public String uploadForm(@RequestParam("thumbnail") MultipartFile file) {
        log.info("file-name: {}", file.getOriginalFilename());
        log.info("file-size: {}", file.getSize());
        log.info("file-type: {}", file.getContentType());

        /*
		 사용자가 첨부한 파일은 DB에 저장하는 것을 선호하지 않습니다.
		 DB 용량을 파일 첨부에 사용하는 것은 비용적으로도 좋지 않기 때문입니다.
		 그렇기 때문에 상용되는 서비스들이 파일을 처리하는 방법은 따로 파일 전용 서버를 구축하여
		 저장하고, DB에는 파일의 저장 경로를 지정하는 것이 일반적입니다.

		 파일 업로드 시 파일명이 동일한 파일이 이미 존재할 수도 있고,
		 사용자가 업로드 하는 파일명이 영어 이외의 언어로 되어있을 수 있습니다.
		 타 언어를 지원하지 않는 환경에서는 정상 동작이 되지 않을 수 있습니다. (리눅스)
		 고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일명을 새롭게 만들어 줍니다.
		 */

        // 첨부파일을 서버 스토리지에 저장.
        // 1. 루트 디렉토리를 생성.
        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs();

        // 2. 랜덤한 문자조합을 이용해서 새 파일명을 생성.
        // UUID: Universally Unique Identifier:
        // 네트워크 상에서 고유성이 보장되는 id를 만들기위한 표준 규약.
        UUID uuid = UUID.randomUUID();
        log.info("uuid: {}", uuid.toString());

        String fileName = uuid.toString();
        fileName = fileName.replace("-", "");

        // 3. 원본 파일명에서 확장자 떼기 (temp.jpg)
        String fileExt
                = file.getOriginalFilename().substring(
                // 뒤에서부터 탐색 했을 때 .부터 끝까지 추출.
                file.getOriginalFilename().lastIndexOf(".")
        );
        log.info("확장자: {}", fileExt);

        // 완성된 경로를 가진 File 객체 생성.
        File uploadFile = new File(rootPath, fileName + fileExt);

        try {
            // 4. 지정한 경로로 파일을 전송.
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}











