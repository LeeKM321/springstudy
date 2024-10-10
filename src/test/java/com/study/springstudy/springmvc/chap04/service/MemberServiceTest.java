package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입을 하면 비밀번호가 암호화 될 것이다.")
    void joinTest() {
        // given
        SignUpRequestDto dto = SignUpRequestDto.builder()
                .account("kim1234")
                .password("kkk1111!")
                .email("kim1234@gmail.com")
                .name("김철수")
                .build();
        // when
        boolean flag = memberService.join(dto);
        // then
        assertTrue(flag);
    }

    @Test
    @DisplayName("id가 존재하지 않는 경우를 테스트한다.")
    void noAccTest() {
        // given
        String account = "park4321";
        String password = "ppp4321!";
        // when
        LoginResult result = memberService.authenticate(account, password);

        // then
        assertEquals(LoginResult.NO_ACC, result);
    }

    @Test
    @DisplayName("pw가 틀린 경우를 테스트한다.")
    void noPwTest() {
        // given
        String account = "kim1234";
        String password = "ppp4321!";
        // when
        LoginResult result = memberService.authenticate(account, password);

        // then
        assertEquals(LoginResult.NO_PW, result);
    }

    @Test
    @DisplayName("로그인이 성공하는 경우를 테스트한다.")
    void loginSuccessTest() {
        // given
        String account = "kim1234";
        String password = "kkk1111!";
        // when
        LoginResult result = memberService.authenticate(account, password);

        // then
        assertEquals(LoginResult.SUCCESS, result);
    }


}













