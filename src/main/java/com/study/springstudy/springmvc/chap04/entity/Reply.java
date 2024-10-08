package com.study.springstudy.springmvc.chap04.entity;

/*
CREATE TABLE tbl_reply (
	reply_no INT AUTO_INCREMENT,
    reply_text VARCHAR(1000) NOT NULL,
    reply_writer VARCHAR(100) NOT NULL,
    reply_date DATETIME DEFAULT current_timestamp,
    board_no INT,

    CONSTRAINT pk_reply PRIMARY KEY(reply_no),
    CONSTRAINT fk_reply FOREIGN KEY(board_no)
    REFERENCES tbl_board(board_no)
    ON DELETE CASCADE
);
 */

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter // 보통 entity는 setter를 필요한 필드에만 직접 구현하는 편입니다.
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    private int replyNo;
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private int boardNo;

}














