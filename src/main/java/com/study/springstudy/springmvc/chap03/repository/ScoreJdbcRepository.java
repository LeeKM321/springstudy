package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScoreJdbcRepository implements ScoreRepository {

    // Spring-jdbc의 핵심 객체 JdbcTemplate의 의존성 주입 (생성자 주입)
    // 데이터베이스 접속 객체(Connection)을 바로 활용하는 것이 가능 -> 미리 세팅을 다 해놓음.
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Score score) {
        String sql = "INSERT INTO score " +
                "(stu_name, kor, eng, math, total, average, grade) " +
                "VALUES(?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, score.getStuName(), score.getKor(),
                score.getEng(), score.getMath(), score.getTotal(),
                score.getAverage(), score.getGrade().toString());
    }


}











