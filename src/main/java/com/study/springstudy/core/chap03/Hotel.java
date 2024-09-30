package com.study.springstudy.core.chap03;

/**
 * @solution
 * - 객체 생성의 제어권을 이 클래스에서
 *   다른 클래스로 이전한다. (AppConfig)
 *   ex) new 생성자(); -> 이 문법을 담당 클래스를 정해서 몰아서 수행시킴
 *   만약 필요로 하는 의존객체가 있다면 미리 주입을 시켜놓는 로직까지 작성.
 *
 *   제어의 역전(IoC): 객체 생성의 제어권을 외부에서 처리하는 것.
 *   의존성 주입(DI): 외부에서 생성된 객체를 주입받는 개념.
 *
 * @problem - 추상화를 했지만 여전히 의존 객체를 바꾸려면
 *            코드를 직접 변경해야 한다.
 */

public class Hotel {

    // 레스토랑
    private Restaurant restaurant;

    // 헤드셰프
    private Chef headChef;

    public Hotel(Restaurant restaurant, Chef headChef) {
        this.restaurant = restaurant;
        this.headChef = headChef;
    }

    // 호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. 그리고 헤드 쉐프는 %s입니다.\n",
                restaurant.getClass().getSimpleName(),
                headChef.getClass().getSimpleName());

        restaurant.order();
    }


}
