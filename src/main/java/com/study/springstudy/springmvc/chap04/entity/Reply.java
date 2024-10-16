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

# 댓글 테이블 구조 변경 (account 추가)
ALTER TABLE tbl_reply
ADD account VARCHAR(50);

# 제약 조건 추가 (member 테이블에 있는 account만 사용이 가능하도록 fk 추가)
ALTER TABLE tbl_reply
ADD CONSTRAINT fk_reply_account
FOREIGN KEY (account)
REFERENCES tbl_member (account)
ON DELETE CASCADE;


 */

import lombok.*;

import java.time.LocalDateTime;

@Getter
// @Setter // 보통 entity는 setter를 필요한 필드에만 직접 구현하는 편입니다.
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    private int replyNo;
    @Setter
    private String replyText;
    @Setter
    private String replyWriter;
    private LocalDateTime replyDate;
    private int boardNo;

    @Setter
    private String account;

    // 조인 필드
    private String profileImage; // member 테이블과 조인을 위해서 추가.
    private String loginMethod; // 댓글 작성자의 로그인 방식을 얻기

}














