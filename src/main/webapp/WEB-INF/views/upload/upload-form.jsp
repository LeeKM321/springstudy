<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      .upload-box {
        width: 150px;
        height: 150px;
        border: 3px dashed orange;
        display: flex;
        justify-content: center;
        align-items: center;
        color: red;
        font-weight: 700;
        cursor: pointer;
      }
      #img-input {
        display: none;
      }
    </style>
  </head>
  <body>
    <h1>파일 업로드 예제</h1>
    <div class="upload-box">파일 첨부</div>
    <!--
      파일 업로드는 기본적으로 post 방식 전송을 진행합니다.
      enctype(인코딩 타입)을 multipart/form-data로 반드시 지정해야 전송됩니다.
    -->
    <form action="/upload-file" method="post" enctype="multipart/form-data">
      <input id="img-input" type="file" name="thumbnail" accept="image/*" />
      <button type="submit">전송</button>
    </form>

    <script>
      const $box = document.querySelector('.upload-box');
      const $input = document.getElementById('img-input');
      $box.onclick = (e) => {
        $input.click();
      };
    </script>
  </body>
</html>
