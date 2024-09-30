package com.study.springstudy.core.chap02;

public class AsianRestaurant implements Restaurant {

    private Chef chef = new KimChef();

    private Course course = new SushiCourse();

    @Override
    public void order() {
        System.out.println("안녕하세요. 아시안 레스토랑입니다.");
        chef.cook();
        course.combineMenu();
    }
}
