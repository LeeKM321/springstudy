package com.study.springstudy.springmvc.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
@Builder
public class Person {

    private String name;
    private int age;
    private List<String> hobby;

}
