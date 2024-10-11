package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Member;
import com.study.springstudy.springmvc.chap04.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.study.springstudy.springmvc.chap04.service.LoginResult.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 처리 서비스
    public boolean join(SignUpRequestDto dto) {
        return memberMapper.save(dto.toEntity(encoder));
    }

    public LoginResult authenticate(String account, String password) {

        // 회원 가입 여부 확인
        Member foundMember = memberMapper.findOne(account);

        if (foundMember == null) {
            return NO_ACC;
        }

        // 비밀번호 일치 검사
        if (encoder.matches(password, foundMember.getPassword())) {
            return SUCCESS;
        } else {
            return NO_PW;
        }

    }

    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }
}














