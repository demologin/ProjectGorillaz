<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 11.12.2024
  Time: 3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>

<!-- Средняя часть -->
<div class="container1">
    <!-- Средняя часть: текст -->
    <div class="text-container">
        <c:choose>
            <c:when test="${isDone != true}">
                <p style="white-space: pre-wrap;">Вопрос: № ${questionNumber}</p>
            </c:when>
        </c:choose>
        <p style="white-space: pre-wrap;">${description}</p>
    </div>

    <c:choose>
        <c:when test="${isDone != true}">
            <form id="authFormRight" method="get" action="game-quiz">
                <input type="text" name="answer" placeholder="Ответ" class="form-control">
                <button type="submit" name="pickedButton" value="BLACK" class="btn">Отправить</button>
            </form>
        </c:when>
        <c:when test="${isDone == true}">
            <form action="start-page" method="post">
                <button type="submit" class="btn" style="white-space: pre-wrap;">ОК</button>
            </form>
        </c:when>
    </c:choose>
</div>

<style>
    /* Основные стили */
    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
        font-family: "Orbitron", sans-serif;
        background-color: #333333;
        color: white;
    }

    .container1 {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 500vh;
        text-align: center;
        padding: 20px; /* Добавлен отступ */
    }

    .btn {
        flex: 1;
        margin: 0 10px;
        padding: 10px;
        text-align: center;
        background-color: black;
        color: white;
        border: none;
        cursor: pointer;
        display: block; /* Убедитесь, что кнопка отображается */
    }
</style>

<%@include file="footer.jsp" %>