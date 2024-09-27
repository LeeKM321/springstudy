<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <h1>[${name}] 취미 목록</h1>
    <ol>
        <c:forEach var="h" items="${hobbies}">
            <li>${h}</li>
        </c:forEach>
    </ol>
    
</body>
</html>