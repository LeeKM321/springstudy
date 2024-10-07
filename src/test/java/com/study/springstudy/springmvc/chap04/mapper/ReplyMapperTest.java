package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("게시물을 100개 등록하고, 랜덤으로 1000개의 댓글을 게시글에 등록한다.")
    void bulkInsertTest() {
        // when
        for (int i = 1; i <= 100; i++) {
            Board b = Board.builder()
                    .title("재밌는 글 " + i)
                    .content("재밌는 내용 " + i)
                    .writer("재밌는 사람 " + i)
                    .build();
            boardMapper.save(b);
        }
        for (int i = 1; i <= 1000; i++) {
            Reply reply = Reply.builder()
                    .replyText("하하호호히히~~~" + i)
                    .replyWriter("잼민이 " + i)
                    .boardNo((int) (Math.random() * 100 + 1))
                    .build();
            replyMapper.save(reply);
        }
    }



}















