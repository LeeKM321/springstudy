package com.study.springstudy.springmvc.util;

import com.study.springstudy.springmvc.chap04.dto.response.LoginUserResponseDTO;
import jakarta.servlet.http.HttpSession;

// 회원 인증, 인가와 관련된 상수와 메서드를 가진 객체
public class LoginUtils {

    // 로그인 세션 키
    public static final String LOGIN_KEY = "login";

    // 로그인 여부 확인
    public static boolean isLogin(HttpSession session) {
        return session.getAttribute(LOGIN_KEY) != null;
    }

    // 로그인 한 사람의 계정명을 반환해 주는 메서드
    public static String getCurrentLoginMemberAccount(HttpSession session) {
        // session.getAttribute의 리턴타입이 Object이기 때문에
        // 자식 고유의 속성 및 기능을 사용하기 위해서는 타입 변환 필요!
        LoginUserResponseDTO dto
                = (LoginUserResponseDTO) session.getAttribute(LOGIN_KEY);
        return dto.getAccount();
    }

}












