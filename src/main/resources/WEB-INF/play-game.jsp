<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<body>


<jsp:useBean id="user" scope="session" type="com.javarush.khmelov.entity.User"/>
<jsp:useBean id="question" scope="request" type="com.javarush.khmelov.entity.Question"/>
<jsp:useBean id="game" scope="request" type="com.javarush.khmelov.entity.Game"/>


<div class="container py-4 py-xl-5">
    <div class="row gy-4 gy-md-0">
        <div class="col-md-6">
            <div class="p-xl-6 m-xl-9">
                <img class="rounded img-fluid w-100 fit-cover" style="min-height: 400px;"
                     src="images/${question.image}">
            </div>
        </div>
        <div class="col-md-6 d-md-flex align-items-md-center">
            <form action="play-game?id=${game.id}" method="post">
                <h2 class="fw-bold">${question.text}</h2>
                <p class="my-3">Выберите вариант ответа</p>
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

                <c:choose>
                    <c:when test="${not empty question.answers}">
                        <button name="game" class="btn btn-primary btn-lg me-2" role="button" id="submit" class="btn btn-success">
                            Отправить
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button id="submit" class="btn btn-warning">Новая игра</button>
                    </c:otherwise>
                </c:choose>
            </form>
            <div style="max-width: 350px;"></div>
        </div>
    </div>
</div>


<c:import url="parts/footer.jsp"/>

