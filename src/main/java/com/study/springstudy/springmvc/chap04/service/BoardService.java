package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDTO;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.PageDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    // mybatis가 우리가 만든 xml을 클래스로 변환해서 빈등록을 해 두기 때문에 주입이 가능하다.
    private final BoardMapper mapper;


    // mapper로부터 전달받은 entity list를 dto list로 변환해서 컨트롤러에게 리턴
    public Map<String, Object> getList(PageDTO page) {
        // 전체 게시글을 가지고 오는것이 아닌, 특정 페이지 부분만 가져와야 함.
        List<Board> boardList = mapper.findAll(page);
        PageMaker pageMaker = new PageMaker(page, mapper.getTotalCount(page));

        List<BoardListResponseDTO> dtoList = boardList.stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("bList", dtoList);
        result.put("pm", pageMaker);
        return result;
    }

    public void register(BoardWriteRequestDTO dto) {
        mapper.save(new Board(dto)); // dto를 entity로 변환해서 mapper에게 전달.
    }

    public BoardDetailResponseDTO getDetail(int bno) {
        // 상세보기니까 조회수를 하나 올려주는 처리를 하자.
        // 상세 조회 전에 실행하자.
        mapper.updateViewCount(bno);
        Board board = mapper.findOne(bno);
        return new BoardDetailResponseDTO(board);
    }

    // 삭제 서비스 로직
    public void delete(int boardNo) {
        mapper.delete(boardNo);
    }
}















