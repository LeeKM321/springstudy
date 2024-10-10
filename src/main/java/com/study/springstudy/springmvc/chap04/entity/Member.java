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


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@Builder
public class Member {
    private String account;
    private String password;
    private String name;
    private String email;

    private Auth auth;
    private LocalDateTime regDate;
}















