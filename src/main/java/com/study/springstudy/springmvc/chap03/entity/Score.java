package com.study.springstudy.springmvc.chap03.entity;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
CREATE TABLE score(
	stu_num INT PRIMARY KEY AUTO_INCREMENT,
    stu_name VARCHAR(30) NOT NULL,
    kor INT NOT NULL DEFAULT 0,
    eng INT NOT NULL DEFAULT 0,
    math INT NOT NULL DEFAULT 0,
    total INT NOT NULL DEFAULT 0,
    average FLOAT,
    grade VARCHAR(1)
);
 */

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

    public Score(ScorePostDTO dto) {
        convertInputData(dto);
        calculateTotalAndAvg();
        makeGrade();
    }

    // 평균에 따른 학점 부여
    private void makeGrade() {
        if (average >= 90) this.grade = Grade.A;
        else if (average >= 80) this.grade = Grade.B;
        else if (average >= 70) this.grade = Grade.C;
        else if (average >= 60) this.grade = Grade.D;
        else this.grade = Grade.F;
    }

    // 전달되는 dto에서 필요한 데이터를 Score의 필드에 할당하는 메서드
    private void convertInputData(ScorePostDTO dto) {
        this.stuName = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
    }

    // 총점과 평균을 계산
    private void calculateTotalAndAvg() {
        this.total = this.kor + this.eng + this.math;
        this.average = this.total / 3.0;
    }




}













