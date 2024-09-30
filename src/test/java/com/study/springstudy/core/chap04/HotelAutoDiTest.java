package com.study.springstudy.core.chap04;

import com.study.springstudy.core.chap04.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HotelAutoDiTest {

    @Test
    void autoDiTest() {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);

        Hotel hotel = ctx.getBean(Hotel.class);
        hotel.inform();
    }

}