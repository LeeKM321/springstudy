package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDTO;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
1. 목록 조회 요청(/board/list: GET)
- req data: x (페이지 번호)
- response: chap04/list.jsp
- res data: 글 목록 리스트 -> model에 담아서 리턴 (bList)
            제목은 5자를 초과하면 안됨.
            내용은 30자를 초과하면 안됨.
            날짜 패턴은 yyyy-MM-dd HH:mm
            글 번호, 조회수, 작성자는 있는 그대로 운반할 것.

2. 글쓰기 화면 요청(/board/write: GET)
- req data: x
- response: chap04/write.jsp
- res data: x

3. 글쓰기 등록 요청(/board/write: POST)
- req data: writer, title, content -> 문자열 타입
- response: 글 목록 페이지 요청이 다시 들어오게끔 (redirect)
- res data: x

4. 글 삭제 요청(/board/delete: POST)
- req data: boardNo -> int
- response: 글 목록 페이지 요청이 다시 들어오게끔 (redirect)
- res data: x

5. 글 상세 보기 요청(/board/detail: GET)
- req data: boardNo -> int
- response: chap04/detail.jsp
- res data: model에 특정 게시글 정보 담아서 리턴

 */



@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardListResponseDTO> list = boardService.getList();
        model.addAttribute("bList", list);
        return "chap04/list";
    }

    @GetMapping("/write")
    public String write() {
        return "chap04/write";
    }

}












