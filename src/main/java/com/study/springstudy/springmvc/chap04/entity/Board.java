package com.study.springstudy.springmvc.chap04.entity;

/*
CREATE TABLE tbl_board (
	board_no INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(2000),
    view_count INT DEFAULT 0,
    reg_date DATETIME DEFAULT current_timestamp,
    writer VARCHAR(50) NOT NULL
);
 */

import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    private int boardNo;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private String writer;

    public Board(BoardWriteRequestDTO dto) {
        this.writer = dto.getWriter();
        this.title = dto.getTitle();
        this.content = dto.getContent();
//        this.regDate = LocalDateTime.now();
    }
}


















