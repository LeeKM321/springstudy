package com.study.springstudy.springmvc.chap04.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class AutoLoginDto {

    private String sessionId;
    private LocalDateTime limitTime;
    private String account;

}
