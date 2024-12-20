<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выберите игру</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css"> <!-- Подключение CSS-файла -->
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body>

<!-- Верхняя часть -->
<c:choose>
<c:when test="${not empty sessionScope.login}">
<header class="header">
    <div class="left-part">
        <p class="stat">Игр: <span>${sessionScope.gamescount}</span></p>
        <p class="stat">Побед: <span>${sessionScope.winscount}</span></p>
        <p class="stat">Поражений: <span>${sessionScope.losscount}</span></p>
    </div>
    <div class="right-part">
        <p class="login"> ${sessionScope.login} к игре готов</p>
    </div>
    </div>
</header>
</c:when>
<c:otherwise>
<header class="header">
    <div class="left-part">
        <img src="${sessionScope.IMG_START_HEAD}" alt="Header Image" class="header-image">
    </div>
    <div class="right-part">
        <div class="auth">
            <!-- Форма для входа -->
            <form id="authForm" method="post">
                <input type="text" name="login" placeholder="Логин" class="form-control">
                <input type="password" name="password" placeholder="Пароль" class="form-control">

                <!-- Кнопка для входа -->
                <button type="submit" onclick="submitForm('login-entrance')" class="btn enter-btn">Войти</button>

                <!-- Кнопка для регистрации -->
                <button type="submit" onclick="submitForm('login-registration')" class="btn">Регистрация</button>
            </form>


        </div>
    </div>
</header>
</c:otherwise>
</c:choose>

<c:if test="${not empty sessionScope.errorMessage}">
<h5 class="mb-1 alert-danger text-white text-center" style="background-color: red;">
        ${sessionScope.errorMessage}
</h5>
</c:if>


