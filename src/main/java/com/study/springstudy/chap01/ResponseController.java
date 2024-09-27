package com.study.springstudy.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ResponseController {

    // JSP 파일로 포워딩할 때 데이터 전달하기
    // 1. Model 객체 사용하기
    @RequestMapping("/hobbies")
    public String hobbies(Model model) {
        model.addAttribute("name", "김철수");
        model.addAttribute("hobbies", List.of("축구", "수영", "영화보기"));

        return "mvc/hobbies";
    }

}













