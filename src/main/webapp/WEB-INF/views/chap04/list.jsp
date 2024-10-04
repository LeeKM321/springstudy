<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <%@ include file="../include/static-head.jsp"  %>

    <link rel="stylesheet" href="/assets/css/list.css">
    

</head>
<body>

    <%@ include file="../include/header.jsp"  %>

    <div id="wrap">
        <div class="main-title-wrapper">
            <h1 class="main-title">꾸러기 게시판</h1>
            <button class="add-btn">새 글 쓰기</button>
        </div>
        <div class="top-section">
            <!-- 검색창 영역 -->
            <div class="search">
                <form action="/board/list" method="get">
                    <select class="form-select" name="type" id="search-type">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
                        <option value="tc">제목+내용</option>
                    </select>
                    <input type="text" class="form-control" name="keyword">
                    <button class="btn btn-primary" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </div>
            <div class="amount">
                <div><a href="#">6</a></div>
                <div><a href="#">18</a></div>
                <div><a href="#">30</a></div>
            </div>
        </div>
        <!-- 메인 게시판 영역 -->
        <div class="card-container">
            <c:forEach var="b" items="${bList}">
                <div class="card-wrapper">
                    <section class="card" data-bno="${b.boardNo}">
                        <div class="card-title-wrapper">
                            <h2 class="card-title">${b.shortTitle}</h2>
                            <div class="time-view-wrapper">
                                <div class="time">
                                    <i class="far fa-clock"></i>
                                        ${b.regDate} </div>
                                <div class="view">
                                    <i class="fas fa-eye"></i>
                                    <span class="view-count">${b.viewCount}</span>
                                </div>
                            </div>
                        </div>
                        <div class="card-content">
                          ${b.shortContent}
                        </div>
                    </section>
                    <div class="card-btn-group">
                        <button class="del-btn" data-href="#">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- 게시글 목록 하단 영역 -->
        <div class="bottom-section">
            <!-- 페이지 버튼 영역 -->
            <nav aria-label="Page navigation example">
                <ul class="pagination pagination-lg pagination-custom">
                        <li class="page-item"><a class="page-link"
                                                 href="#">&lt;&lt;</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="#">prev</a>
                        </li>
                        <li data-page-num="" class="page-item">
                            <a class="page-link"
                               href="#">${i}</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="#">next</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="#">&gt;&gt;</a>
                        </li>
                </ul>
            </nav>
        </div>
    </div>
    </div>
    <!-- 모달 창 -->
    <div class="modal" id="modal">
        <div class="modal-content">
            <p>정말로 삭제할까요?</p>
            <div class="modal-buttons">
                <button class="confirm" id="confirmDelete"><i class="fas fa-check"></i> 예</button>
                <button class="cancel" id="cancelDelete"><i class="fas fa-times"></i> 아니오</button>
            </div>
        </div>
    </div>
    
    <script>

        // 카드 형태의 게시물들을 감싸고 있는 부모 요소 취득
        const $cardContainer = document.querySelector('.card-container');

        $cardContainer.addEventListener('click', e => {
            if (e.target.matches('.card-container')) return;
            
            // section 태그에 붙은 글 번호를 읽어오자.
            // 이벤트가 발생된 타겟이 지금 계속 달라지고 있다. -> 고정된 상대 경로로는 번호를 얻기 힘들다.
            // $item.closest(특정 요소 선택자) -> $item에서 가장 가까운 특정 요소를 취득하게 해 줌.
            const bno = e.target.closest('section.card').dataset.bno;
            console.log('글 번호: ', bno);            

            // 서버에 요청 보내기
            location.href='/board/detail/' + bno;
            
        })


      //========== 게시물 목록 스크립트 ============//
      function removeDown(e) {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card-wrapper');
        $targetCard?.removeAttribute('id', 'card-down');
      }
      function removeHover(e) {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card');
        $targetCard?.classList.remove('card-hover');
        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '0';
      }
      $cardContainer.onmouseover = e => {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card');
        $targetCard?.classList.add('card-hover');
        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '1';
      }
      $cardContainer.onmousedown = e => {
        if (e.target.matches('.card-container .card-btn-group *')) return;
        const $targetCard = e.target.closest('.card-wrapper');
        $targetCard?.setAttribute('id', 'card-down');
      };
      $cardContainer.onmouseup = removeDown;
      $cardContainer.addEventListener('mouseout', removeDown);
      $cardContainer.addEventListener('mouseout', removeHover);
      
      // write button event
      document.querySelector('.add-btn').onclick = e => {
        window.location.href = '/board/write';
      };
    </script>


</body>
</html>












