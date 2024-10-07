package com.study.springstudy.springmvc.chap04.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

// 실제 에러가 발생하면 미리 만들어 놓은 에러페이지로 안내하는 설정.
@Configuration
public class CustomErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        // 응답 상태에 따른 요청을 세팅
        ErrorPage errorPage404
                = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");

        ErrorPage errorPage500
                = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

        registry.addErrorPages(errorPage404, errorPage500);
    }
}







