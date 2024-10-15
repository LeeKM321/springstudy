<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <input
      type="text"
      name="email"
      id="userEmail"
      placeholder="이메일을 입력하세요."
    />
    <button type="button" id="mail-check-btn">이메일 인증</button>
    <br />
    <input
      type="text"
      id="mail-check-input"
      placeholder="인증번호 6자리를 입력하세요."
      maxlength="6"
      disabled
    />
    <br />
    <span id="mailCheckMsg"></span>

    <script>
      let code = ''; // 이메일 전송 인증번호를 전역변수로 선언.

      // 이메일 인증버튼 클릭 이벤트
      document.getElementById('mail-check-btn').onclick = () => {
        // 우선 올바른 이메일 형식인지, 중복이 발생하지 않았는지 먼저 체크하기.
        // 여기에서는 따로 진행하지 않겠습니다. (sign-up.jsp에는 이미 되어있음)
        const email = document.getElementById('userEmail').value.trim();
        console.log('완성된 email: ', email);

        fetch('/members/email', {
          method: 'post',
          headers: {
            'Content-type': 'text/plain',
          },
          body: email,
        })
          .then((res) => {
            if (res.status === 200) {
              return res.text();
            } else {
              alert('존재하지 않는 이메일 주소인거 같아요!');
              return;
            }
          })
          .then((data) => {
            console.log('인증번호: ', data);
            alert('인증번호가 전송되었습니다. 입력란에 정확히 입력해 주세요!');
            document.getElementById('mail-check-input').disabled = false;
            code = data; // 서버가 전달한 인증번호를 전역변수에 저장.
          });
      };

      // 인증번호 검증
      // blur -> focus가 빠지는 경우 발생.
      document.getElementById('mail-check-input').onblur = (e) => {
        console.log('blur 이벤트 발생!');
        const inputCode = e.target.value;

        if (inputCode === code) {
          document.getElementById('mailCheckMsg').textContent =
            '인증번호가 일치합니다!';
          document.getElementById('mailCheckMsg').style.color = 'skyblue';
          e.target.style.display = 'none';
        } else {
          document.getElementById('mailCheckMsg').textContent =
            '인증번호를 다시 확인하세요!';
          document.getElementById('mailCheckMsg').style.color = 'red';
          e.target.focus();
        }
      };
    </script>
  </body>
</html>
