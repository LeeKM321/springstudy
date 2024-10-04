package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    // mybatis가 우리가 만든 xml을 클래스로 변환해서 빈등록을 해 두기 때문에 주입이 가능하다.
    private final BoardMapper mapper;


    // mapper로부터 전달받은 entity list를 dto list로 변환해서 컨트롤러에게 리턴
    public List<BoardListResponseDTO> getList() {
        List<Board> boardList = mapper.findAll();

        return boardList.stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList());
    }
}















