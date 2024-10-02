package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Spring 컨테이너의 빈을 활용한 테스트 클래스를 활용.
@SpringBootTest
class ScoreJdbcRepositoryTest {

    @Autowired
    ScoreJdbcRepository repository;

    @Test
    @DisplayName("새로운 성적 정보를 save를 통해 추가한다.")
    void saveTest() {
        ScorePostDTO dto = new ScorePostDTO("홍길동", 45, 56, 68);
        Score score = new Score(dto);

        repository.save(score);
    }

    @Test
    @DisplayName("score 테이블의 모든 학생 목록을 조회하면 학생의 수가 2명일 것이다.")
    void findAllTest() {
        // given - 테스트에 필요한 값을 세팅

        // when - 테스트 주요 코드 실행 (테스트 실행 목표)
        List<Score> scoreList = repository.findAll("num");
        System.out.println("scoreList = " + scoreList);

        // then - 테스트 결과를 검증 (Assertion: 단언)
        assertEquals(scoreList.size(), 2);
    }

    @Test
    @DisplayName("2번 학생의 이름은 홍길동일 것이다.")
    void findOneTest() {
        // given
        int stuNum = 25;

        // when
        Score score = repository.findOne(stuNum);

        // then
        if (score != null) {
            assertEquals("홍길동", score.getStuName());
        } else {
            assertNull(score);
        }
    }

    @Test
    @DisplayName("2번 학생을 삭제한 후에는 더 이상 2번 학생이 조회되지 않아야 한다.")
    void deleteTest() {
        // given
        int stuNum = 2;

        // when
        repository.delete(stuNum);
        Score score = repository.findOne(stuNum);

        // then
        assertNull(score);
    }

    @Test
    @DisplayName("7번 학생의 국어, 수학점수를 수정 후, 다시 조회하면 수정된 데이터가 조회되어야 한다.")
    void updateTest() {
        // given
        int stuNum = 7, kor = 76, math = 88;
        Score score = repository.findOne(stuNum);
        ScorePostDTO dto
                = new ScorePostDTO(score.getStuName(), kor, score.getEng(), math);
        System.out.println("dto = " + dto);

        // when
        Score score2 = new Score(dto);
        score2.setStuNum(stuNum); // 번호 초기화!
        repository.update(score2);

        // then
        Score changeScore = repository.findOne(stuNum);
        System.out.println("changeScore = " + changeScore);
        assertNotEquals(score.getTotal(), changeScore.getTotal());
    }


}














