package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.LoginRequestDto;
import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDto;
import com.study.springstudy.springmvc.chap04.dto.response.LoginUserResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Member;
import com.study.springstudy.springmvc.chap04.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
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

    public LoginResult authenticate(LoginRequestDto dto) {

        // 회원 가입 여부 확인
        Member foundMember = memberMapper.findOne(dto.getAccount());

        if (foundMember == null) {
            return NO_ACC;
        }

        // 비밀번호 일치 검사
        if (encoder.matches(dto.getPassword(), foundMember.getPassword())) {
            return SUCCESS;
        } else {
            return NO_PW;
        }

    }

    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }

    public void maintainLoginState(HttpSession session, String account) {
        // 세션은 서버에서만 유일하게 보관되는 데이터로서
        // 로그인 유지 등 상태 유지가 필요할 때 사용되는 내장 객체입니다.
        // 세션은 쿠키와 달리 모든 데이터를 저장할 수 있으며 크기도 제한이 없습니다.
        // 세션의 수명은 기본 1800초 -> 원하는 만큼 수명을 설정할 수 있습니다.
        // 브라우저가 종료되면 남은 수명에 상관없이 세션 데이터는 소멸합니다.

        // 현재 로그인 한 회원의 모든 정보 조회
        Member foundMember = memberMapper.findOne(account);

        // DB 데이터를 사용할 것만 정제
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(foundMember.getAccount())
                .name(foundMember.getName())
                .email(foundMember.getEmail())
                .build();

        // 세션에 로그인 한 회원 정보를 저장
        session.setAttribute("login", dto);
        // 세션 수명 설정
        session.setMaxInactiveInterval(60 * 60); // 1시간

    }
}














