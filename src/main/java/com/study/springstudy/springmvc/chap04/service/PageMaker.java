package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class PageMaker {

    // 페이지의 시작번호와 끝번호, 보정된 끝 페이지 번호
    private int begin, end, finalPage;

    // 이전, 다음 버튼 활성화 여부
    private boolean prev, next;

    // 현재 사용자가 요청한 페이지 정보.
    private PageDTO page;

    // 총 게시물의 개수
    private int totalCount;

    // 한 화면에 보여질 페이지 버튼 개수
    private static final int PAGE_COUNT = 10;

    // 계산에 필요한 값은 객체가 생성될 때 전달 받겠습니다.
    public PageMaker(PageDTO page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;

        makePageInfo();
    }

    // 페이징 알고리즘 전담 메서드
    private void makePageInfo() {

        // 끝 페이지 번호 (end) 계산
        this.end = (int) (Math.ceil((double)page.getPageNo() / PAGE_COUNT) * PAGE_COUNT);

        // 시작 페이지 번호 (begin) 계산
        this.begin = this.end - PAGE_COUNT + 1;

        // 이전 버튼 활성화 여부 (prev)
//        this.prev = begin == 1 ? false : true;
        this.prev = begin > 1;

        // 진짜 마지막 페이지가 몇인지를 먼저 구하자.
        this.finalPage = (int) Math.ceil((double)totalCount / page.getAmount());

        // 마지막 페이지 구간에서 end값을 finalPage 값으로 변경
        if (this.end > this.finalPage) {
            this.end = this.finalPage;
        }

        // 다음 버튼 활성화 여부 (next)
        this.next = this.end < this.finalPage;


    }
}












