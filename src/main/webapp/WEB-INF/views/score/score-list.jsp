<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <!-- reset css -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css"
    />
    <!-- bootstrap css -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- bootstrap js -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      defer
    ></script>
    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
      label {
        display: block;
      }
      .score-list > li {
        margin-bottom: 10px;
      }
      .score-list > li:first-child {
        font-size: 1.2em;
        color: blue;
        font-weight: 700;
        border-bottom: 1px solid skyblue;
      }
      .del-btn {
        width: 10px;
        height: 10px;
        background: red;
        color: #fff;
        border-radius: 5px;
        margin-left: 5px;
        text-decoration: none;
        font-size: 0.7em;
        padding: 6px;
      }
      .del-btn:hover {
        background: orangered;
      }
      section.score {
        padding: 200px 50px 100px;
        font-size: 1.5em;
      }
      .list-header {
        display: flex;
        justify-content: space-between;
        width: 50%;
      }
      .list-header .sort-link-group {
        display: flex;
      }
      .list-header .sort-link-group div {
        margin-right: 20px;
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <section class="score">
        <h1>시험 점수 등록</h1>
        <form action="/score/register" method="POST">
          <label> # 이름: <input type="text" name="name" /> </label>
          <label> # 국어: <input type="text" name="kor" /> </label>
          <label> # 영어: <input type="text" name="eng" /> </label>
          <label> # 수학: <input type="text" name="math" /> </label>
          <label>
            <button type="submit">확인</button>
            <button id="go-home" type="button">홈화면으로</button>
          </label>
        </form>

        <hr />

        <form action="/score/remove" method="post" name="removeForm">
          <ul class="score-list">
            <li class="list-header">
              <div class="count">총 학생 수: ${sList.size()}명</div>
              <div class="sort-link-group">
                <div><a href="/score/list?sort=num">학번순</a></div>
                <div><a href="/score/list?sort=name">이름순</a></div>
                <div><a href="/score/list?sort=avg">평균순</a></div>
              </div>
            </li>
            <c:forEach var="s" items="${sList}">
              <li>
                # 학번: ${s.stuNum}, 이름:
                <a href="/score/detail?stuNum=${s.stuNum}">${s.maskingName}</a>,
                평균: ${s.average}점, 학점: ${s.grade}
                <a class="del-btn" href="${s.stuNum}">삭제</a>
              </li>
            </c:forEach>
            <input type="hidden" name="stuNum" id="stu-num" />
          </ul>
        </form>
      </section>
    </div>

    <script>
      // 삭제 버튼 클릭 이벤트 처리
      const $ul = document.querySelector('.score-list');

      $ul.addEventListener('click', (e) => {
        // 삭제 버튼이 아니라면 이벤트 강제 종료
        if (!e.target.matches('.del-btn')) return;

        e.preventDefault(); // a 태그의 고유기능 중지.

        if (confirm('정말로 삭제하시겠습니까?')) {
          // a태그에 미리 세팅해 놓은 href에 작성된 학생 번호를 얻어오자.
          const stuNum = e.target.getAttribute('href');
        }
      });
    </script>
  </body>
</html>
