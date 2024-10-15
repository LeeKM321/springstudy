<%@ page language="java" contentType="text/html;charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>게시판 글쓰기</title>
    <%@include file="../include/static-head.jsp" %>
    <style>
      .form-container {
        width: 500px;
        margin: auto;
        padding: 20px;
        background-image: linear-gradient(135deg, #a1c4fd, #fbc2eb);
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        border-radius: 4px;
        font-size: 18px;
      }
      .form-container h1 {
        font-size: 40px;
        font-weight: 700;
        letter-spacing: 10px;
        text-align: center;
        margin-bottom: 20px;
        color: #ffffff;
      }
      .form-container h2 {
        font-size: 30px;
        color: #222;
        text-align: center;
        margin-bottom: 20px;
      }
      label {
        display: block;
        margin-bottom: 5px;
        font-size: 20px;
      }
      #title,
      #writer {
        font-size: 18px;
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
        border: 2px solid #ffffff;
        border-radius: 8px;
        margin-bottom: 10px;
        background-color: rgba(255, 255, 255, 0.8);
      }
      #content {
        height: 400px;
        font-size: 18px;
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
        border: 2px solid #ffffff;
        border-radius: 8px;
        margin-bottom: 10px;
        background-color: rgba(255, 255, 255, 0.8);
      }
      textarea {
        resize: none;
        height: 200px;
      }
      .buttons {
        display: flex;
        justify-content: flex-end;
        margin-top: 20px;
      }
      button {
        font-size: 20px;
        padding: 10px 20px;
        border: none;
        margin-right: 10px;
        background-color: #4caf50;
        color: white;
        cursor: pointer;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        transition: background-color 0.3s;
      }
      button.list-btn {
        background: #e61e8c;
      }
      button:hover {
        background-color: #3d8b40;
      }
      button.list-btn:hover {
        background: #e61e8c93;
      }
      /* 페이지 css */
      /* 페이지 액티브 기능 */
      .pagination .page-item.p-active a {
        background: #333 !important;
        color: #fff !important;
        cursor: default;
        pointer-events: none;
      }
      .pagination .page-item:hover a {
        background: #888 !important;
        color: #fff !important;
      }
      /* 댓글 프로필 */
      .profile-box {
        width: 70px;
        height: 70px;
        border-radius: 50%;
        overflow: hidden;
        margin: 10px auto;
      }
      .profile-box img {
        width: 100%;
      }
      .reply-profile {
        width: 30px;
        height: 30px;
        border-radius: 50%;
        margin-right: 10px;
      }
    </style>
  </head>
  <body>
    <%@include file="../include/header.jsp" %>
    <div id="wrap" class="form-container">
      <h1>${b.boardNo}번 게시물 내용~</h1>
      <h2># 작성일자: ${b.regDate}</h2>
      <label for="writer">작성자</label>
      <input
        type="text"
        id="writer"
        name="writer"
        value="${b.writer}"
        readonly
      />
      <label for="title">제목</label>
      <input type="text" id="title" name="title" value="${b.title}" readonly />
      <label for="content">내용</label>
      <div id="content">${b.content}</div>
      <div class="buttons">
        <c:if test="${login != null && login.account == b.writer}">
          <button
            class="list-btn"
            type="button"
            onclick="location.href='/board/modify?boardNo=${b.boardNo}'"
          >
            수정
          </button>
        </c:if>
        <button
          class="list-btn"
          type="button"
          onclick="location.href='/board/list?pageNo=${s.pageNo}&amount=${s.amount}&type=${s.type}&keyword=${s.keyword}'"
        >
          목록
        </button>
      </div>

      <!-- 댓글 영역 -->
      <div id="replies" class="row">
        <div class="offset-md-1 col-md-10">
          <!-- 댓글 쓰기 영역 -->
          <div class="card">
            <div class="card-body">
              <c:if test="${login == null}">
                <a href="/members/sign-in"
                  >댓글은 로그인 후에 작성할 수 있습니다.</a
                >
              </c:if>

              <c:if test="${login != null}">
                <div class="row">
                  <div class="col-md-9">
                    <div class="form-group">
                      <label for="newReplyText" hidden>댓글 내용</label>
                      <textarea
                        rows="3"
                        id="newReplyText"
                        name="replyText"
                        class="form-control"
                        placeholder="댓글을 입력해주세요."
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <div class="profile-box">
                        <c:if test="${login.profile == null}">
                          <img src="/assets/img/anonymous.jpg" alt="프사" />
                        </c:if>
                        <c:if test="${login.profile != null}">
                          <img src="/display${login.profile}" alt="프사" />
                        </c:if>
                      </div>
                      <label for="newReplyWriter" hidden>댓글 작성자</label>
                      <input
                        id="newReplyWriter"
                        name="replyWriter"
                        type="text"
                        class="form-control"
                        placeholder="작성자 이름"
                        style="margin-bottom: 6px"
                        value="${login.name}"
                        readonly
                      />
                      <button
                        id="replyAddBtn"
                        type="button"
                        class="btn btn-dark form-control"
                      >
                        등록
                      </button>
                    </div>
                  </div>
                </div>
              </c:if>
            </div>
          </div>
          <!-- end reply write -->
          <!--댓글 내용 영역-->
          <div class="card">
            <!-- 댓글 내용 헤더 -->
            <div class="card-header text-white m-0" style="background: #343a40">
              <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
            </div>
            <!-- 댓글 내용 바디 -->
            <div id="replyCollapse" class="card">
              <div id="replyData">
                <!--
                        < JS로 댓글 정보 DIV삽입 >
                    -->
              </div>
              <!-- 댓글 페이징 영역 -->
              <ul class="pagination justify-content-center">
                <!--
                        < JS로 댓글 페이징 DIV삽입 >
                    -->
              </ul>
            </div>
          </div>
          <!-- end reply content -->
        </div>
      </div>
      <!-- end replies row -->
      <!-- 댓글 수정 모달 -->
      <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header" style="background: #343a40; color: white">
              <h4 class="modal-title">댓글 수정하기</h4>
              <button
                type="button"
                class="close text-white"
                data-bs-dismiss="modal"
              >
                X
              </button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
              <div class="form-group">
                <input id="modReplyId" type="hidden" />
                <label for="modReplyText" hidden>댓글내용</label>
                <textarea
                  id="modReplyText"
                  class="form-control"
                  placeholder="수정할 댓글 내용을 입력하세요."
                  rows="3"
                ></textarea>
              </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
              <button id="replyModBtn" type="button" class="btn btn-dark">
                수정
              </button>
              <button
                id="modal-close"
                type="button"
                class="btn btn-danger"
                data-bs-dismiss="modal"
              >
                닫기
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- end replyModifyModal -->
    </div>

    <script>
      const URL = '/api/v1/replies'; // 댓글과 관련된 요청 url을 전역화.
      const bno = '${b.boardNo}';

      const currentAccount = '${login.account}'; // 로그인중인 사람 계정
      const auth = '${login.auth}'; // 로그인 한 사람 권한

      const $addBtn = document.getElementById('replyAddBtn');

      // 화면에 댓글 태그들을 렌더링하는 함수
      function renderReplies(replyData) {
        // 객체 디스트럭쳐링 (댓글 수, 페이지메이커, 댓글목록으로 분해)
        const { count, pageInfo, replies } = replyData;

        let tag = '';

        if (replies !== null && replies.length > 0) {
          for (let reply of replies) {
            // 객체 디스트럭쳐링
            const { rno, writer, text, regDate, account, profile } = reply;

            tag += `
                    <div id='replyContent' class='card-body' data-replyId='\${rno}'>
                        <div class='row user-block'>
                            <span class='col-md-8'>
                        `;

            tag += profile
              ? `<img class='reply-profile' src='/display\${profile}' alt='profile image' >`
              : `<img class='reply-profile' src='/assets/img/anonymous.jpg' alt='anonymous image' >`;

            tag += `<b>\${writer}</b>
                            </span>
                            <span class='col-md-4 text-right'><b>\${regDate}</b></span>
                        </div><br>
                        <div class='row'>
                            <div class='col-md-9'>\${text}</div>
                            <div class='col-md-3 text-right'>
                        `;
            if (auth === '관리자회원' || currentAccount === account) {
              tag += `
                  <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                  <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
              `;
            }

            tag += `   </div>
                            </div>
                        </div>
                        `;
          }
        } else {
          tag +=
            "<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>";
        }
        // 댓글 수 렌더링
        document.getElementById('replyCnt').textContent = replies.length;

        // 반복문을 이용해서 문자열로 작성한 tag를 댓글 영역 div에 innerHTML로 삽입.
        document.getElementById('replyData').innerHTML = tag;

        // 페이지 렌더링 함수 호출
        renderPages(pageInfo);
      }

      // 화면에 페이지 버튼들을 렌더링하는 함수.
      // 매개변수 선언부에 처음부터 디스트럭쳐링 해서 받고 있음.
      function renderPages({ begin, end, prev, next, page, finalPage }) {
        let tag = '';

        // 이전 버튼 만들기
        if (prev) {
          tag += `<li class='page-item'><a class='page-link page-active' href='\${begin - 1}'>이전</a></li>`;
        }

        // 페이지 번호 버튼 만들기
        for (let i = begin; i <= end; i++) {
          let active = '';
          if (page.pageNo === i) {
            active = 'p-active';
          }

          tag += `<li class='page-item \${active}'><a class='page-link page-custom' href='\${i}'>\${i}</a></li>`;
        }

        // 다음 버튼 만들기
        if (next) {
          tag += `<li class='page-item'><a class='page-link page-active' href='\${end + 1}'>다음</a></li>`;
        }

        // 페이지 태그 렌더링
        document.querySelector('.pagination').innerHTML = tag;
      }

      // 서버에 비동기 방식으로 댓글 목록을 받아오는 함수
      function fetchGetReplies(pageNum = 1) {
        // 댓글 목록 요청시 페이지번호 전달(전달 안되면 기본값 1)
        // 자바스크립트 문자열 안에 달러와 중괄호를 쓰면 el로 인식.
        // 달러기호 앞에 백슬래시 붙여주세요.
        fetch(`\${URL}/\${bno}/page/\${pageNum}`)
          .then((res) => res.json())
          .then((replyList) => {
            console.log(replyList);
            renderReplies(replyList);
          });
      }

      // 페이지 클릭 이벤트 핸들러 등록 함수
      function makePageButtonClickHandler() {
        console.log('페이지 버튼이 클릭됨!');

        const $pageUl = document.querySelector('.pagination');

        $pageUl.addEventListener('click', (e) => {
          if (!e.target.matches('.page-item a')) return;
          e.preventDefault(); // a 기능 죽이기

          // href에 작성된 각각의 페이지 번호를 가져와서 댓글 목록을 비동기 요청.
          fetchGetReplies(e.target.getAttribute('href'));
        });
      }

      // 댓글 등록
      $addBtn.onclick = (e) => {
        const $replyText = document.getElementById('newReplyText'); // 댓글 내용
        const $replyWriter = document.getElementById('newReplyWriter'); // 댓글 작성자
        // 공백이 제거된 값을 얻음.
        const textVal = $replyText.value.trim();
        const writerVal = $replyWriter.value.trim();
        // 사용자 입력값 검증
        if (textVal === '') {
          alert('댓글 내용은 필수값입니다!!');
          return;
        } else if (writerVal === '') {
          alert('댓글 작성자는 필수값입니다!!');
          return;
        } else if (writerVal.length < 2 || writerVal.length > 8) {
          alert('댓글 작성자는 2글자에서 8글자 사이로 작성하세요!');
          return;
        }

        // 서버로 데이터 보낼 준비
        const payload = {
          text: textVal,
          author: writerVal,
          bno: bno,
        };

        // 요청 방식 및 데이터를 전달할 정보 객체 만들자 (POST)
        const requestInfo = {
          method: 'POST',
          headers: {
            'content-type': 'application/json',
          },
          body: JSON.stringify(payload),
        };

        // 서버에 POST 요청 보내기
        fetch(URL, requestInfo).then((res) => {
          console.log(res.status); // 서버에서 전달한 응답 상태 코드

          if (res.status === 200) {
            alert('댓글이 정상 등록되었습니다.');
            // json 데이터일 때는 json(), text일 때는 text();

            // 비동기 요청이기 때문에 페이지가 이동하지 않습니다.
            // 다음 댓글 입력을 위해 입력창을 비워주세요.
            $replyText.value = '';
            $replyWriter.value = '';

            // 등록 완료 후 목록 요청하기.
            fetchGetReplies();
          } else {
            alert('입력값에 문제가 있습니다. 입력값을 다시 확인해 보세요!');
            console.log(res.text());
          }
        });
      };

      // 댓글 삭제 + 수정 모드 진입 이벤트 핸들러 등록 및 처리함수
      function makeReplyRemoveClickHandler() {
        // 댓글 목록 전체를 감싸고 있는 영역 취득
        const $replyData = document.getElementById('replyData');

        $replyData.addEventListener('click', (e) => {
          e.preventDefault(); // a태그 죽이기

          // 수정이든 삭제든 댓글 번호가 필요합니다.
          // 그래서 미리 얻어놓도록 하겠습니다.
          const rno = e.target.closest('#replyContent').dataset.replyid;

          if (e.target.matches('#replyDelBtn')) {
            // 삭제 로직 진행
            if (!confirm('정말 삭제할까요?')) return;

            /*
            URL: /api/v1/replies/rno: DELETE, 전달되는 JSON은 없습니다.
            headers랑 body는 작성할 필요가 없습니다.
            삭제 완료 후에는 1페이지 댓글 목록 요청이 들어가도록 처리.
            */

            fetch(`\${URL}/\${rno}`, {
              method: 'DELETE',
            }).then((res) => {
              if (res.status === 200) {
                alert('댓글이 삭제되었습니다.');
                fetchGetReplies();
              } else {
                alert('오류가 발생했습니다. 관리자에게 문의하세요!');
              }
            });
          } else if (e.target.matches('#replyModBtn')) {
            // 수정 모드 진입 (모달)
            // 기존에 작성한 댓글 내용을 가져오자.
            const replyText =
              e.target.parentNode.previousElementSibling.textContent;

            // 읽어온 댓글 내용을 모달 바디에 때려 넣자.
            document.getElementById('modReplyText').value = replyText;

            // 댓글 번호도 모달 안에 있는 input hidden에다가 집어 넣어야 함.
            document.getElementById('modReplyId').value = rno;
          } else return;
        });
      }

      // 모달 안에서 수정 버튼 클릭시 이벤트 처리 함수
      function makeReplyModifyClickHandler() {
        const $modBtn = document.getElementById('replyModBtn');

        $modBtn.addEventListener('click', (e) => {
          const payload = {
            rno: document.getElementById('modReplyId').value,
            text: document.getElementById('modReplyText').value,
          };

          const requestInfo = {
            method: 'PATCH',
            headers: {
              'content-type': 'application/json',
            },
            body: JSON.stringify(payload),
          };

          fetch(URL, requestInfo).then((res) => {
            if (res.status === 200) {
              // modal 닫기
              document.getElementById('modal-close').click();
              fetchGetReplies(); // 수정 완료 후 1페이지 댓글 목록 요청이 들어가게끔 처리.
            } else {
              alert('수정값에 문제가 있습니다. 내용을 확인하세요!');
              document.getElementById('modReplyText').value = '';
              return;
            }
          });
        });
      }

      // 즉시 실행 함수를 이용해서 페이지가 로딩되면 함수가 자동 호출되게 하자.
      (() => {
        // 댓글을 서버에서 불러오기.
        fetchGetReplies();

        // 페이지 번호 클릭 이벤트 등록
        makePageButtonClickHandler();

        // 댓글 삭제 & 수정 모드 이벤트 등록
        makeReplyRemoveClickHandler();

        // 댓글 수정 요청 이벤트 핸들러
        makeReplyModifyClickHandler();
      })();
    </script>
  </body>
</html>
