<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<div class="container">
    <br>
    <h2>Статистика</h2>
    <br>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Логин</th>
            <th scope="col">В процессе</th>
            <th scope="col">Победа</th>
            <th scope="col">Поражение</th>
            <th scope="col">Всего</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stat" items="${requestScope.listUserStatistics}">
            <tr>
                <td scope="row">${stat.login}</td>
                <td>${stat.play}</td>
                <td>${stat.win}</td>
                <td>${stat.lost}</td>
                <td>${stat.total}</td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <c:set var="all" value="${requestScope.totalUserStatistics}"/>
        <tr class="table-active">
            <th scope="col">${all.login}</th>
            <th scope="col">${all.play}</th>
            <th scope="col">${all.win}</th>
            <th scope="col">${all.lost}</th>
            <th scope="col">${all.total}</th>
        </tr>
        </tbody>
    </table>
</div>
<c:import url="parts/footer.jsp"/>

