package com.study.springstudy.core.chap03.config;

import com.study.springstudy.core.chap03.Chef;
import com.study.springstudy.core.chap03.JannChef;
import com.study.springstudy.core.chap03.KimChef;
import com.study.springstudy.core.chap03.*;

// 객체 생성의 제어권을 모두 가지고 온 객체
public class AppConfig {

    // 쉐프 객체 생성
    public Chef chef1() {
        return new KimChef();
    }
    public Chef chef2() {
        return new JannChef();
    }

    // 요리 코스 객체 생성
    public Course course1() {
        return new SushiCourse();
    }
    public Course course2() {
        return new FrenchCourse();
    }

    // 레스토랑 객체 생성
    public Restaurant restaurant1() {
        return new AsianRestaurant(chef1(), course1());
    }

    public Restaurant restaurant2() {
        return new WesturnRestaurant(chef2(), course2());
    }

    // 호텔 객체 생성
    public Hotel hotel() {
        return new Hotel(restaurant1(), chef1());
    }


}












