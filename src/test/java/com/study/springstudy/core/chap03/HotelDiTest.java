package com.study.springstudy.core.chap03;

import com.study.springstudy.core.chap03.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HotelDiTest {

    @Test
    void diTest() {
        AppConfig config = new AppConfig();

        Hotel hotel = config.hotel();
        hotel.inform();
    }

    @Test
    void springDiTest() {
        // 스프링 컨테이너(등록한 빈(객체)을 담아놓는 컨테이너)를 로딩하는 객체를 생성.
        // 설정 파일(AppConfig) 클래스 로드.
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // Hotel 클래스 타입으로 등록된 bean을 리턴받는 메서드.
        Hotel hotel = ctx.getBean(Hotel.class);
        // 호텔 기능 사용.
        hotel.inform();
    }



}











