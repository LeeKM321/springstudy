package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.request.BoardWriteRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.SearchDTO;
import com.study.springstudy.springmvc.chap04.dto.response.BoardDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
- req data: writer, title, content -> 문자열 타입 (BoardWriteRequestDTO)
            DTO를 board로 바꿔서 mapper에게 전달해야 함. -> Board의 생성자를 이용.
- response: 글 목록 페이지 요청이 다시 들어오게끔 (redirect)
- res data: x

4. 글 삭제 요청(/board/delete: POST)
- req data: boardNo -> int
- response: 글 목록 페이지 요청이 다시 들어오게끔 (redirect)
- res data: x

5. 글 상세 보기 요청(/board/detail/글번호: GET)
- req data: url 경로에 글번호가 묻어서 옴.
- response: chap04/detail.jsp
- res data: model에 특정 게시글 정보 담아서 리턴

 */



@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 목록 요청 (페이징과 검색 기능을 곁들인)
    @GetMapping("/list")
    public String list(Model model,
                       @ModelAttribute("s") SearchDTO page) {
        Map<String, Object> map = boardService.getList(page);
        model.addAttribute("bList", map.get("bList"));
        model.addAttribute("maker", map.get("pm"));
        return "chap04/list";
    }

    @GetMapping("/write")
    public String write() {
        return "chap04/write";
    }

    // 글 등록 요청
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto,
                        HttpSession session) {
        log.info("/board/write: POST, dto: {}", dto);
        boardService.register(dto, session);
        return "redirect:/board/list";
    }

    // /board/detail/23
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable int bno,
                         // model에 직접 데이터를 담는 로직을 생략할 수 있는 @ModelAttribute
                         @ModelAttribute("s") SearchDTO page,
                         Model model) {
        BoardDetailResponseDTO dto = boardService.getDetail(bno);
        model.addAttribute("b", dto);
//        model.addAttribute("p", page);
        return "chap04/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int boardNo) {
        System.out.println("/board/delete: POST!!" + boardNo);
        boardService.delete(boardNo);

        return "redirect:/board/list";
    }

}












