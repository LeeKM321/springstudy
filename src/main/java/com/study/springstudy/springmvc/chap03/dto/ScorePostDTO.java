package com.study.springstudy.springmvc.chap03.dto;

import lombok.*;

// 역할: 브라우저가 요청과 함께 전달한 성적 정보를 포장하는 객체
@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScorePostDTO {

    private String name;
    private int kor;
    private int eng;
    private int math;

}
