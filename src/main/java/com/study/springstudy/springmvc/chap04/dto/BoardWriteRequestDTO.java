package com.study.springstudy.springmvc.chap04.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class BoardWriteRequestDTO {

    private String writer;
    private String title;
    private String content;

}
