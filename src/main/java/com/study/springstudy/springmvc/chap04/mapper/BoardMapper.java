package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 목록 조회
    List<Board> findAll();

    // 상세 조회
    Board findOne(int boardNo);

    // 게시글 등록
    void save(Board board);

    // 게시글 삭제
    void delete(int boardNo);

    // 조회수 처리
    void updateViewCount(int bno);

}
