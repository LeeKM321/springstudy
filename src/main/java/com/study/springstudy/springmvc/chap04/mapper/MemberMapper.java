package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.dto.request.AutoLoginDto;
import com.study.springstudy.springmvc.chap04.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    // 중복 확인 (아이디, 이메일)
    // 어떤걸 중복 검사할 것인지를 type으로 받기. (account OR email)
    // 값을 keyword로 받자.
    boolean existsById(String type, String keyword);

    // 회원 가입
    boolean save(Member member);

    // 회원 정보 개별 조회
    Member findOne(String account);

    // 자동 로그인 세션 아이디, 만료시간 업데이트
    void saveAutoLogin(AutoLoginDto dto);
}














