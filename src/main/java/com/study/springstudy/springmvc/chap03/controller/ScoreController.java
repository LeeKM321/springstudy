package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.dto.ScoreResponseDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import com.study.springstudy.springmvc.chap03.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    # 요청 URL
    1. 학생 성적정보 등록화면 및 성적정보 목록조회 처리
    - /score/list : GET

    2. 성적 정보 등록 처리 요청
    - /score/register : POST

    3. 성적정보 삭제 요청
    - /score/remove : POST

    4. 성적정보 상세 조회 요청
    - /score/detail : GET
 */


@Controller
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService service;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "num") String sort,
                       Model model) {
        System.out.println("/score/list: GET!");

        model.addAttribute("sList", service.findAll(sort));
        return "score/score-list";
    }

    @PostMapping("/register")
    public String register(ScorePostDTO dto) {
        System.out.println("/score/register: POST!");
        System.out.println("dto = " + dto);

        service.save(dto);

        // 등록이 완료되었다면 목록 화면으로 데이터를 전달해서 점수 목록을 보여주고 싶다.
        // 목록 처리를 다른 메서드가 하고 있다. -> 중복 로직을 작성할 필요는 없다.
        // redirect를 통해 /score/list라는 요청이 다시 들어오게끔 유도. ->
        return "redirect:/score/list";
    }

    // 성적 상세 조회 요청
    @GetMapping("/detail")
    public String detail(@RequestParam int stuNum,
                         Model model) {
        System.out.println("/score/detail: GET!!");
        retrieve(stuNum, model);
        return "score/score-detail";
    }

    // 성적 삭제 요청
    @PostMapping("/remove")
    public String remove(@RequestParam int stuNum) {
        System.out.println("/score/remove: POST!");
        System.out.println("stuNum = " + stuNum);

        service.remove(stuNum);
        return "redirect:/score/list";
    }

    // 수정 페이지로 이동 요청
    @GetMapping("/modify")
    public String modify(@RequestParam int stuNum,
                         Model model) {
        System.out.println("/score/modify: GET!!");
        retrieve(stuNum, model);

        return "score/score-modify";
    }

    // 진짜 수정 처리 요청
    // 서비스, 레파지토리 계층과 연계하여 update 처리를 진행해 주세요.
    // 수정이 완료된 후 사용자에게 응답할 페이지는
    // 최신 수정 내용이 반영된 detail 페이지 입니다. -> redirect
    @PostMapping("/modify")
    public String modify(ScorePostDTO dto, // kor, eng, math는 dto로 받고
                         @RequestParam int stuNum) { // stuNum은 dto가 못받으니까 따로 받음.
        service.update(dto, stuNum);

        return "redirect:/score/detail?stuNum=" + stuNum;
    }


    private void retrieve(int stuNum, Model model) {
        Score score = service.findOne(stuNum);
        model.addAttribute("stu", score);
    }


}











