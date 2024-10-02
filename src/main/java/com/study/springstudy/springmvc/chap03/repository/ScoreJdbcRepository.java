package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.entity.Grade;
import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScoreJdbcRepository implements ScoreRepository {

    class ScoreMapper implements RowMapper<Score> {

        // ResultSet: sql 실행 결과(조회)의 내용을 갖고 있는 객체.
        // 타겟을 한 행씩 지목하면서 컬럼값을 가져올 수 있다.
        // mapRow 메서드를 통해 jdbcTemplate한테 알려주자 -> 한 행의 컬럼값을
        // Score 객체로 포장하는 방법을.
        @Override
        public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Score(
                    rs.getInt("stu_num"),
                    rs.getString("stu_name"),
                    rs.getInt("kor"),
                    rs.getInt("eng"),
                    rs.getInt("math"),
                    rs.getInt("total"),
                    rs.getDouble("average"),
                    Grade.valueOf(rs.getString("grade"))
            );
        }
    }

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

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM score";

        switch (sort) {
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }

        // 여러 행이 조회될 때는 query()를 호출.
        // sql, RowMapper 인터페이스를 구현한 객체를 전달.
        // 조회된 내용을 어떤 방식으로 포장할 지를 알려줘야 한다. (테이블은 컬럼의 타입, 개수 등이 모두 다르기 때문)
        return jdbcTemplate.query(sql, new ScoreMapper());
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num = ?";

        try {
            // queryForObject()는 조회 결과가 없을 시 예외가 발생합니다.
            return jdbcTemplate.queryForObject(sql, new ScoreMapper(), stuNum);
        } catch (DataAccessException e) {
            // 조회 결과가 없다면 catch문 실행 -> null을 리턴.
            return null;
        }
    }

    @Override
    public void delete(int stuNum) {
        String sql = "DELETE FROM score WHERE stu_num = ?";
        jdbcTemplate.update(sql, stuNum);
    }

    @Override
    public void update(Score score) {
        String sql = "UPDATE score" +
                " SET kor=?, eng=?, math=?, total=?, average=?, grade=?" +
                " WHERE stu_num=?";

        jdbcTemplate.update(sql, score.getKor(), score.getEng(),
                score.getMath(), score.getTotal(), score.getAverage(),
                score.getGrade().toString(), score.getStuNum());

    }


}











