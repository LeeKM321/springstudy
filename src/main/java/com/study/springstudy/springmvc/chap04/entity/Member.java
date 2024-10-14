package com.study.springstudy.springmvc.chap04.entity;

/*
-- 회원 관리 테이블
CREATE TABLE tbl_member (
    account VARCHAR(50),
    password VARCHAR(150) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    auth VARCHAR(20) DEFAULT 'COMMON',
    reg_date DATETIME DEFAULT current_timestamp,

    CONSTRAINT pk_member PRIMARY KEY (account)
);
 */


import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private String account;
    private String password;
    private String name;
    private String email;

    private Auth auth;
    private LocalDateTime regDate;

    // 새롭게 추가된 컬럼에 맞게 필드를 추가 (자동 로그인)
    private String sessionId;
    private LocalDateTime limitTime;

    private String profileImage; // 프로필 사진 이미지 경로
}















