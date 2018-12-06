<%--
  Created by IntelliJ IDEA.
  User: Valentin
  Date: 04.12.2018
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Сортировка книг по жанру и издательству</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/book-filter" method="post">
        Жанр
        <select name="genre" id="genre">
            <option></option>
            <c:forEach var="genre" items="${requestScope.genre}">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select><br>
        Издательство:
            <input type="radio" name="publishingHouse" value="АСТ"> АСТ
            <input type="radio" name="publishingHouse" value="Эксмо"> ЭКСМО
            <input type="radio" name="publishingHouse" value="Амфора"> Амфора
            <p>Количество книг в списке</p>
            <p><input type="number" name="limit" value="1" min="1" max="100" step="1"></p>
        <p>Начальная позиция в списке</p>
        <p><input type="number" name="offset" value="0" min="1" max="99" step="1"></p>
            <input type="submit" value="Поиск">
    </form>
</div>
</body>
</html>