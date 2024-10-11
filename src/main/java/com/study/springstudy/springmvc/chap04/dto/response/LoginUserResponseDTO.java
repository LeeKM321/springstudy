package com.study.springstudy.springmvc.chap04.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @Builder
public class LoginUserResponseDTO {

    private String account;
    private String name;
    private String email;
    private String auth;

}
