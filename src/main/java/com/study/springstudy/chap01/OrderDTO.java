package com.study.springstudy.chap01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 커맨드 객체 생성.
// 요청과 함께 전달되는 데이터가 많을 경우
// 스프링에게 객체 형태로 파라미터를 전달해 달라고 요구할 수 있다.
@Getter @Setter
@ToString
public class OrderDTO {

    // 필드 선언 -> 필드명이 쿼리 파라미터 변수명과 동일해야 합니다.
    // getter, setter를 구현하기 위해 정보 은닉을 구현해야 합니다.
    private int orderNum;
    private String goods;
    private int amount;
    private int price;



}
