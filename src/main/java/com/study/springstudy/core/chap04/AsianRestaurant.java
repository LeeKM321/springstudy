package com.study.springstudy.core.chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AsianRestaurant implements Restaurant {

    private Chef chef;

    private Course course;

    @Autowired
    public AsianRestaurant(@Qualifier("kim") Chef chef,
                           @Qualifier("sushi") Course course) {
        this.chef = chef;
        this.course = course;
    }

    @Override
    public void order() {
        System.out.println("안녕하세요. 아시안 레스토랑입니다.");
        chef.cook();
        course.combineMenu();
    }
}
