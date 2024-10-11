package com.study.springstudy.springmvc.chap04.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@Builder
public class LoginRequestDto {

    private String account;
    private String password;
    private Boolean autoLogin;

}
