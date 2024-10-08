package com.study.springstudy.springmvc.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/rest")
public class RestControllerTest {

    @GetMapping("/view")
//    @ResponseBody
    public String viewPage() {
        return "hello";
    }

    /*
    @RequestBody
    - 클라이언트 쪽에서 전송하는 JSON 데이터를
     서버에서 사용하는 자바 언어에 맞게 변환하여 받을 수 있는 아노테이션.

    @ResponseBody
    - 메서드가 리턴하는 데이터를 viewResolver에게 전달하지 않고,
    클라이언트에게 해당 데이터를 바로 응답하게 합니다.
    데이터는 JSON으로 변환해서 응답이 나갑니다.
    비동기 통신에서 주로 많이 사용합니다.
     */

    @PostMapping("/object")
//    @ResponseBody
    public Person object(@RequestBody Person p) {
        System.out.println("/rest/object 요청: POST");
        System.out.println("p = " + p);

        return Person.builder()
                .name("김빌더")
                .age(100)
                .hobby(List.of("빌드하기", "놀기", "밥먹기"))
                .build();
    }

    @PostMapping("/object2")
//    @ResponseBody
    public ResponseEntity<?> object2(@RequestBody Map<String, Object> map) {
        System.out.println("/rest/object 요청: POST");
        System.out.println("map = " + map);

        //ResponseEntity: 응답하고자 하는 데이터와 함께, 상태 코드 및 헤더 등을 같이 전달할 수 있는
        // REST 응답용 객체.

        return ResponseEntity.badRequest().body("이게 뭔요청이야!");
    }


}











