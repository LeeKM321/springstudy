package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void saveTest() {
        // given
        Member m = Member.builder()
                .account("abc1234")
                .password("aaa1111!")
                .name("김춘식")
                .email("abc1234@naver.com")
                .build();
        // when
        boolean flag = memberMapper.save(m);

        // then
        assertTrue(flag);
    }

    @Test
    @DisplayName("계정명이 abc1234인 회원은 중복 확인 결과가 true여야 한다.")
    void existsTest() {
        // given
        String acc = "abc1234";
        // when
        boolean flag = memberMapper.existsById("account", acc);
        // then
        assertTrue(flag);
    }

    @Test
    @DisplayName("이메일이 abc1234@naver.com인 회원은 중복 확인 결과가 true여야 한다.")
    void existsEmailTest() {
        // given
        String email = "abc1234@naver.com";
        // when
        boolean flag = memberMapper.existsById("email", email);
        // then
        assertTrue(flag);
    }


}
















