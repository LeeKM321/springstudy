package com.study.springstudy.core.chap01;

public class WesturnRestaurant {

    private JannChef chef = new JannChef();

    private FrenchCourse course = new FrenchCourse();

    public void order() {
        System.out.println("안녕하세요. 서양 레스토랑입니다.");
        chef.cook();
        course.combineMenu();
    }

}
