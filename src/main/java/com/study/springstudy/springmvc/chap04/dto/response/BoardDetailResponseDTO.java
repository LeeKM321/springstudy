package com.study.springstudy.springmvc.chap04.dto.response;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class BoardDetailResponseDTO {

    private final int boardNo;
    private final String title;
    private final String content;
    private final String regDate;
    private final String writer;

    private int replyCount; // 목록에서 각 게시물 당 댓글 수 보여주는 전용 필드.

    public BoardDetailResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.regDate = BoardListResponseDTO.makePrettierDateString(board.getRegDate());
        this.writer = board.getWriter();
    }
}













