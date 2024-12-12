<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<div class="container">

    <jsp:useBean id="user" scope="session" type="com.javarush.khmelov.entity.User"/>
    <jsp:useBean id="question" scope="request" type="com.javarush.khmelov.entity.Question"/>
    <jsp:useBean id="game" scope="request" type="com.javarush.khmelov.entity.Game"/>

    <div class="px-3 py-3 my-3 text-left">

        <div class="form-group text-center">
            <img src="images/${question.image}"
                 class="img-fluid"
                 style="height: 50%; display: block;"
                 alt="${question.image}">
        </div>
        <p class="lead mb-2">

        <h1>${question.text}</h1>
        <form action="play-game?id=${game.id}" method="post">
            <ul>
                <c:forEach var="answer" items="${question.answers}">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="answer" value="${answer.id}"
                               id="answer${answer.id}">
                        <label class="form-check-label" for="answer${answer.id}">
                                ${answer.text}
                        </label>
                    </div>
                </c:forEach>
            </ul>
            <input type="hidden" name="questId" value="${game.questId}">
            <div class=" form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${not empty question.answers}">
                            <button id="submit" class="btn btn-success">Отправить</button>
                        </c:when>
                        <c:otherwise>
                            <button id="submit" class="btn btn-warning">Новая игра</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </div>
</div>
<c:import url="parts/footer.jsp"/>

