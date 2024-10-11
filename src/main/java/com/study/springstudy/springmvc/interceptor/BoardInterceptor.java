package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.util.LoginUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.study.springstudy.springmvc.util.LoginUtils.*;

@Configuration
public class BoardInterceptor implements HandlerInterceptor {

    // 컨트롤러로 요청이 들어가기 전에 실행할 내용.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 로그인 안 했으면 글쓰기, 글 수정, 글 삭제 요청을 튕겨낼 것.
        HttpSession session = request.getSession();

        if (!isLogin(session)) {
            System.out.println("권한 없음! 요청 거부!");
            response.sendRedirect("/members/sign-in");
            return false;
        }
        return true;
    }

    // 컨트롤러로 요청이 들어간 후 공통적으로 처리할 코드나 검사할 일들을 실행할 내용.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}













