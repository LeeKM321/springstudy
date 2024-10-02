package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    private final ScoreJdbcRepository repository;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "num") String sort,
                       Model model) {
        System.out.println("/score/list: GET!");

        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);

        return "score/score-list";
    }

    @PostMapping("/register")
    public String register(ScorePostDTO dto) {
        System.out.println("/score/register: POST!");
        System.out.println("dto = " + dto);

        repository.save(new Score(dto));

        // 등록이 완료되었다면 목록 화면으로 데이터를 전달해서 점수 목록을 보여주고 싶다.
        // 목록 처리를 다른 메서드가 하고 있다. -> 중복 로직을 작성할 필요는 없다.
        // redirect를 통해 /score/list라는 요청이 다시 들어오게끔 유도. ->
        return "redirect:/score/list";
    }



}











