package com.study.springstudy.core.chap03.config;

import com.study.springstudy.core.chap03.Chef;
import com.study.springstudy.core.chap03.JannChef;
import com.study.springstudy.core.chap03.KimChef;
import com.study.springstudy.core.chap03.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링에게 객체 생성 등의 제어를 맡길 때 사용하는 어노테이션.
// 수동으로 Bean(객체)를 등록할 때 클래스에 선언하는 문법.
//@Configuration("app")
public class AppConfig {

    /*
    // 쉐프 객체 생성
    @Bean // 메서드에서 특정 객체가 생성되어 리턴될 때, 객체의 생명 주기를 Spring이 관리하도록 설정.
    public Chef chef1() {
        return new KimChef();
    }
    @Bean
    public Chef chef2() {
        return new JannChef();
    }

    // 요리 코스 객체 생성
    @Bean
    public Course course1() {
        return new SushiCourse();
    }
    @Bean
    public Course course2() {
        return new FrenchCourse();
    }

    // 레스토랑 객체 생성
    @Bean
    public Restaurant restaurant1() {
        return new AsianRestaurant(chef1(), course1());
    }

    @Bean
    public Restaurant restaurant2() {
        return new WesturnRestaurant(chef2(), course2());
    }

    // 호텔 객체 생성
    @Bean
    public Hotel hotel() {
        return new Hotel(restaurant1(), chef1());
    }
     */


}












