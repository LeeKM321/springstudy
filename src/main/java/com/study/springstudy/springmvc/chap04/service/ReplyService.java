package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyModifyRequestDto;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyPostRequestDto;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyListResponseDto;
import com.study.springstudy.springmvc.chap04.entity.Reply;
import com.study.springstudy.springmvc.chap04.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper mapper;

    public void register(ReplyPostRequestDto dto) {
        mapper.save(dto.toEntity());
    }

    public ReplyListResponseDto getList(int boardNo, int pageNo) {
        PageDTO page = new PageDTO();
        page.setAmount(5);
        page.setPageNo(pageNo);

        List<Reply> replyList = mapper.findAll(boardNo, page);

        List<ReplyDetailResponseDto> dtoList = new ArrayList<>();
        replyList.forEach(reply -> dtoList.add(new ReplyDetailResponseDto(reply)));

        return ReplyListResponseDto.builder()
                .count(dtoList.size())
                .pageInfo(new PageMaker(page, mapper.count(boardNo)))
                .replies(dtoList)
                .build();

    }

    public void modify(ReplyModifyRequestDto dto) {
        mapper.modify(dto.toEntity());
    }

    public void delete(int replyNo) throws Exception {
        mapper.delete(replyNo);
    }
}
















