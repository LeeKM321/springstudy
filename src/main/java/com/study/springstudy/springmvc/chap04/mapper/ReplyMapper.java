package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // 댓글 등록
    void save(Reply reply);

    // 댓글 수정 (내용)
    void modify(Reply reply);

    // 댓글 삭제
    void delete(int replyNo);

    // 댓글 개별 조회
    Reply findOne(int replyNo);

    // 댓글 전체 목록 조회
    // 댓글 전체 목록 조회 (페이징)
    // 마이바티스에서 mapper에 매개변수가 2개 이상일 때는 변수명으로 바로 지목할 수 없습니다.
    // (스프링 3점대 -> 마이바티스 3버전 이상을 사용하는데, 얘는 변수명으로 지목되는 것을 확인함!!!)
    // 1. 변수를 하나만 쓰자 (전달하고자 하는 값이 여러 개라면 객체로 한번에 포장해서 넘긴다.)
    // 2. Map으로 포장해서 넘기자. (Map의 key가 xml에서 지목하는 이름이 된다.)
    // 3. @Param을 사용해서 직접 이름 붙이기

    List<Reply> findAll(@Param("bn") int boardNo,
                        @Param("p") PageDTO page);

    // 댓글 총 개수 조회
    int count(int boardNo);

}











