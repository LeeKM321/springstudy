package com.study.springstudy.springmvc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FileUtils {

    /**
     * 사용자가 클라이언트에서 파일을 전송했을 때
     * 중복이 없는 새로운 파일명을 생성하여 해당 파일명으로
     * 날짜별 폴더로 업로드하는 메서드
     *
     * @param profileImage - 사용자가 업로드한 파일의 정보객체
     * @param rootPath - 서버에 업로드할 루트 디렉토리 경로
     *                   ex) D:/spring-prj/upload
     * @return - 업로드가 완료되었을 경우 업로드 된 파일의 위치 경로
     *                   ex)  /2023/12/29/djfalsjdflaksjlfdsaj_고양이.jpg
     */
    public static String uploadFile(MultipartFile profileImage,
                                    String rootPath) {

        String newFileName = UUID.randomUUID()
                                .toString()
                                .replace("-", "");

        // 확장자 추출.
        String FileExt = profileImage.getOriginalFilename()
                .substring(profileImage.getOriginalFilename().lastIndexOf("."));

        File uploadPath = new File(rootPath, newFileName + FileExt);
        try {
            profileImage.transferTo(uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("완성된 경로: {}", uploadPath.getPath());
        return uploadPath.getPath();
    }




}







