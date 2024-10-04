package com.study.springstudy.springmvc.chap03.mapper;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("마이바티스 insert 테스트")
    void insertTest() {
        // given
        Score score
                = new Score(new ScorePostDTO("김마이바티스", 87, 56, 75));
        // when
        mapper.save(score);

        // then
    }

    @Test
    @DisplayName("마이바티스 select 테스트")
    void selectTest() {
        // given

        // when
        Score score = mapper.findOne(890);

        // then
        System.out.println(score);
    }

}










