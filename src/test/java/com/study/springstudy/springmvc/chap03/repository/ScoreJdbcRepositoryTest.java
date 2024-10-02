package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


}














