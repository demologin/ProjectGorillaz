<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<body>
<div class="container">
    <h5>${requestScope.quest.name}</h5>
    <c:forEach var="question" items="${requestScope.quest.questions}">
        <a id="bookmark${question.id}"></a>
        <form class="row row-cols-lg-auto g-3 align-items-center"
              action="quest"
              method="post" enctype="multipart/form-data"
              id="form${question.id}">
            <input name="text" class="w-100" type="text" value="${question.text}">
            <img src="images/${question.image}" class="w-25" alt="${question.text}" height="10%">
            <input name="image" class="w-25" type="file">
            <button type="submit" class="btn btn-primary">Обновить</button>
            <input name="questionId" type="hidden" value="${question.id}">
            <input name="id" type="hidden" value="${requestScope.quest.id}">
        </form>
    </c:forEach>
</div>
<c:import url="parts/footer.jsp"/>

