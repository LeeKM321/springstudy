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

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int boardNo;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private String writer;

}


















