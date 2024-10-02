package com.study.springstudy.springmvc.chap03.dto;

import com.study.springstudy.springmvc.chap03.entity.Grade;
import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 서버가 클라이언트에게 데이터를 전달할 때
 * 보여줄 데이터만 선별해서 응답하도록 사용할 객체.
 * (쓸데없는 데이터나, 민감한 정보가 들어있는 데이터는 전달할 필요가 없음)
 */
//@RequiredArgsConstructor -> final 필드 초기화하는 생성자.
@Getter @ToString
//@Setter -> setter는 구현하지 않겠습니다. 응답용이기 때문에 더이상의 데이터 변경을 막기 위해서.
public class ScoreResponseDTO {

    private final int stuNum;
    private final String maskingName;
    private final double average;
    private final Grade grade;

    // 생성자의 매개값으로 Score 원형 객체가 전달되면
    // 필요한 데이터만 뽑아서 DTO로 만들자.
    public ScoreResponseDTO(Score score) {
        this.stuNum = score.getStuNum();
        this.maskingName = makeMaskingName(score.getStuName());
        this.average = score.getAverage();
        this.grade = score.getGrade();
    }

    private String makeMaskingName(String stuName) {
        // 학생의 성만 빼고 나머지 이름을 *로 가려주는 기능
        StringBuilder maskedName
                = new StringBuilder(String.valueOf(stuName.charAt(0))); // 성만 추출

        // 성 빼고 나머지 이름의 길이만큼 반복해서 *을 붙이자.
        for (int i = 0; i < stuName.length() - 1; i++) {
            maskedName.append("*");
        }

        return maskedName.toString();
    }
}













