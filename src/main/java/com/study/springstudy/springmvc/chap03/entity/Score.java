package com.study.springstudy.springmvc.chap03.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 역할: 데이터베이스의 테이블의 컬럼과 1:1로 매칭되는 필드를 가진 객체
@Getter @Setter
@ToString
public class Score {

    private int stuNum;
    private String stuName;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;
    private Grade grade;

}
