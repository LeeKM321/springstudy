package com.study.springstudy.springmvc.chap03.service;

/*
    컨트롤러와 레파지토리 사이에 위치하여
    중간 처리를 담당

    - 트랜잭션 처리, 데이터 가공 처리....

    - 의존 관계
    Controller -> Service -> Repository
 */

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.dto.ScoreResponseDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.mapper.ScoreMapper;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreMapper repository;

    public List<ScoreResponseDTO> findAll(String sort) {
         /*
        컨트롤러는 데이터베이스에서 성적정보 리스트를
        조회해 오기를 원하고 있다.
        그런데 데이터베이스는 민감한 정보까지 모두 조회한다.
        그리고 컬럼명도 그대로 노출하기 때문에
        이 모든것을 숨기는 처리를 하고 싶다. -> response용 DTO를 생성하자!
        */
        List<Score> scoreList = repository.findAll(sort);

        return scoreList
                .stream()
                .map(score -> new ScoreResponseDTO(score))
                .collect(Collectors.toList());
    }

    public void save(ScorePostDTO dto) {
        repository.save(new Score(dto));
    }

    // 원래라면 응답용 DTO를 생성해서 가공한 후 컨트롤러에게 넘기는 것이 원칙이지만
    // 지금은 그냥 Entity Score를 넘기도록 하겠습니다.
    public Score findOne(int stuNum) {
        return repository.findOne(stuNum);
    }

    public void remove(int stuNum) {
        repository.delete(stuNum);
    }

    public void update(ScorePostDTO dto, int stuNum) {
        Score changeScore = new Score(dto);
        changeScore.setStuNum(stuNum); // 학번 넣어야 합니다!!!!
        repository.update(changeScore);
    }
}













