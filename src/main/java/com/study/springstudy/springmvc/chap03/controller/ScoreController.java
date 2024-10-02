package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.dto.ScoreResponseDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
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

    private final ScoreJdbcRepository repository;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "num") String sort,
                       Model model) {
        System.out.println("/score/list: GET!");

        List<Score> scoreList = repository.findAll(sort);

        /*
        컨트롤러는 데이터베이스에서 성적정보 리스트를
        조회해 오기를 원하고 있다.
        그런데 데이터베이스는 민감한 정보까지 모두 조회한다.
        그리고 컬럼명도 그대로 노출하기 때문에
        이 모든것을 숨기는 처리를 하고 싶다. -> response용 DTO를 생성하자!
        */
//        List<ScoreResponseDTO> dtoList = new ArrayList<>();
//        for (Score score : scoreList) {
//            ScoreResponseDTO dto = new ScoreResponseDTO(score);
//            dtoList.add(dto);
//        }

        List<ScoreResponseDTO> dtoList = scoreList
                .stream()
                .map(score -> new ScoreResponseDTO(score))
                .collect(Collectors.toList());


        model.addAttribute("sList", dtoList);

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











