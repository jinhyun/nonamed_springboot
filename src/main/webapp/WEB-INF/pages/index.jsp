<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    Welcome to ${message}
    <ul>세션정보
        <li>
            <input type="text" value="${userSession.userId}">
        </li>
        <li>
            <input type="text" value="${userSession.userName}">
        </li>
    </ul>
</body>
</html>