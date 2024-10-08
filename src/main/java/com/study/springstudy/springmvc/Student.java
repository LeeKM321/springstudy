package com.study.springstudy.springmvc;

/*
 Builder 패턴을 이용해서 Student 객체를 생성.

 1. 가독성 향상: 메서드 이름을 필드랑 최대한 비슷하게 -> 어떤 필드에 어떤 값이 들어가는지를 좀 더 명확히 할 수 있음.
 2. 유연성 향상: 생성자에 비해 더 편하게 전달 순서 상관 없이, 원하는 필드만 초기화 가능.
 3. 불변 객체: 객체 생성 후 변하지 않게끔 유지 가능. 생성자 private, 매개값으로 Builder만 받음.
 */

public class Student {

    private String name;
    private int age;
    private String grade;
    private String major;

    // 값 세팅이 완료된 Builder를 통해 Student를 초기화하는 생성자.
    private Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.grade = builder.grade;
        this.major = builder.major;
    }


    // 내부 builder 클래스 선언
    public static class Builder {
        // 원본 객체(Student)와 동일하게 필드 선언.
        private String name;
        private int age;
        private String grade;
        private String major;

        // 필드값을 설정하기 위한 Builder 메서드 선언
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder major(String Major) {
            this.major = major;
            return this;
        }

        // 최종적으로 원본 객체(Student)를 생성해서 반환하는 메서드
        public Student build() {
            return new Student(this);
        }

    }



}














