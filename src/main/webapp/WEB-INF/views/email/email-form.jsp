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
        });
      };
    </script>
  </body>
</html>
