package com.study.springstudy.core.chap03;

public class WesturnRestaurant implements Restaurant {

    private Chef chef;

    private Course course;

    public WesturnRestaurant(Chef chef, Course course) {
        this.chef = chef;
        this.course = course;
    }

    @Override
    public void order() {
        System.out.println("안녕하세요. 서양 레스토랑입니다.");
        chef.cook();
        course.combineMenu();
    }

}
