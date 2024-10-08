package com.study.springstudy.springmvc.chap04.dto.request;

import com.study.springstudy.springmvc.chap04.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter @Getter @ToString
@Builder
public class ReplyPostRequestDto {

    // NotNull: null만 안됨. 공백은 됨!
    // NotBlank: null 안됨! 공백도 안됨!

    @NotBlank
    @Size(min = 1, max = 300)
    private String text;

    @NotBlank
    @Size(min = 2, max = 8)
    private String author;

    @NotNull
    private int bno;

    // dto를 entity로 바꾸는 변환 메서드
    public Reply toEntity() {
        return Reply.builder()
                .replyText(text)
                .replyWriter(author)
                .boardNo(bno)
                .build();
    }

}













