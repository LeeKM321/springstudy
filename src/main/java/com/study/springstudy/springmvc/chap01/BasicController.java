package com.study.springstudy.springmvc.chap01;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/spring/chap01")
public class BasicController {

    // URL: /spring/chap01/hello
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("/hello 요청이 들어옴!");
        return "hello";
    }

    // ============ 요청 파라미터 (Query String) 읽기 ============
    // URL: /spring/chap01/person

    // 1. HttpServletRequest 사용 (잘 사용 x)
    @RequestMapping("/person")
    public String person(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }

    // 2. @RequestParam 사용하기
    // URL: /spring/chap01/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(@RequestParam String stu,
                        @RequestParam("major") String mj,
                        int grade) {

        System.out.println("stu = " + stu);
        System.out.println("mj = " + mj);
        System.out.println("grade = " + grade);

        return "";
    }

    // 3. 커맨드 객체 (request DTO) 사용하기
    // URL: /spring/chap01/order?orderNum=22&goods=구두&amount=3&price=20000&.....
    @RequestMapping("/order")
    public String order(OrderDTO dto) {

        System.out.println(dto);
        return "";
    }

}












