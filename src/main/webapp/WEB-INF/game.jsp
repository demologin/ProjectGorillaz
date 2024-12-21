<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sessionScope.quest.name}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/game.css">
</head>
<body>

<div class="container">
    <div class="content">
        <img src="${sessionScope.stage.image}" alt="${sessionScope.stage.title}">
        <div class="text">
            <h1>${sessionScope.stage.title}</h1>
            <p>${sessionScope.stage.text}</p>
        </div>
    </div>

    <div class="answers">
        <form action="${pageContext.request.contextPath}/game" method="POST">
            <input type="hidden" name="questId" value="${sessionScope.quest.id}">
            <c:choose>
                <c:when test="${sessionScope.stage.selector == 'NONE'}">
                    <input type="hidden" name="stageId" value="${sessionScope.stage.defaultOption.nextQuestStageId}">
                </c:when>

                <c:when test="${sessionScope.stage.selector == 'OPTIONS'}">
                    <label>
                        <input type="radio" name="stageId" value="${sessionScope.stage.option1.nextQuestStageId}" required>
                            ${sessionScope.stage.option1.text}
                    </label>
                    <label>
                        <input type="radio" name="stageId" value="${sessionScope.stage.option2.nextQuestStageId}">
                            ${sessionScope.stage.option2.text}
                    </label>
                </c:when>

                <c:when test="${sessionScope.stage.selector == 'INPUT'}">
                    <input type="hidden" name="stageId" value="${sessionScope.stage.defaultOption.nextQuestStageId}">
                    <label>
                        <input type="text" name="userInput" required>
                    </label>
                </c:when>
            </c:choose>


            <button type="submit">${sessionScope.stage.button}</button>

            <c:if test="${sessionScope.stage.state == 'WON' or sessionScope.stage.state == 'LOST'}">
                <button type="submit" formaction="/" formmethod="GET">Другой квест</button>
            </c:if>
        </form>
    </div>
</div>

<c:if test="${sessionScope.stage.state == 'WON' or sessionScope.stage.state == 'LOST'}">
    <div class="statistics">
        <table>
            <tr>
                <td>
                    <div class="this-statistics">
                        <h3>Статистика квеста</h3>
                        <c:choose>
                            <c:when test="${sessionScope.quest.id == 1}">
                                <p>Имя капитана: ${sessionScope.userInput}</p>
                            </c:when>
                            <c:when test="${sessionScope.quest.id == 2}">
                                <p>Имя дракончика: ${sessionScope.userInput}</p>
                            </c:when>
                            <c:when test="${sessionScope.quest.id == 3}">
                                <p>Ваше желание: ${sessionScope.userInput}</p>
                            </c:when>
                        </c:choose>
                        <p>Количество сыгранных игр: ${sessionScope.quest.totalGames}</p>
                        <p>Побед: ${sessionScope.quest.wins}</p>
                        <p>Проигрышей: ${sessionScope.quest.loses}</p>
                    </div>
                </td>

                <td>
                    <div class="general-statistics">
                        <h3>Общая статистика</h3>
                        <p>IP адрес: ${sessionScope.clientIp}</p>
                        <p>Общее количество сыгранных игр: ${sessionScope.generalStats.totalGames}</p>
                        <p>Всего побед: ${sessionScope.generalStats.totalWins}</p>
                        <p>Всего проигрышей: ${sessionScope.generalStats.totalLoses}</p>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</c:if>

</body>
</html>