<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 18.09.2018
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Список книг</title>
</head>
<body>
<div>
    <c:forEach items="${requestScope.books}" var="book">
        <p>${book.id} -${book.genre.name}-${book.name}
            -${book.publishingHouse.name}-${book.yearPublishing}-${book.pages}</p>
    </c:forEach>
</div>
</body>
</html>
