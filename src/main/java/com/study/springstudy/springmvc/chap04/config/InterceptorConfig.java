package com.study.springstudy.springmvc.chap04.config;

import com.study.springstudy.springmvc.interceptor.AfterLoginInterceptor;
import com.study.springstudy.springmvc.interceptor.AutoLoginInterceptor;
import com.study.springstudy.springmvc.interceptor.BoardInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 내가 만든 인터셉터들을 스프링 컨텍스트에 등록하는 설정 파일
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AfterLoginInterceptor afterLoginInterceptor;
    private final BoardInterceptor boardInterceptor;
    private final AutoLoginInterceptor autoLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(afterLoginInterceptor) // 어떤 인터셉터 등록?
                .addPathPatterns("/members/sign-in", "/members/sign-up"); // 언제 동작?

        registry
                .addInterceptor(boardInterceptor)
                .addPathPatterns("/board/*")
                .excludePathPatterns("/board/list", "/board/detail/*"); // 인터셉터 발동을 제외할 패턴

        registry
                .addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/**");
    }
}



























