<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>

<!-- Средняя часть -->
<main class="main">
    <!-- Бегущая строка -->
    <div class="marquee">
        <h1>ВЫБЕРИТЕ ИГРУ</h1>
    </div>

    <div class="games">
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_QUEST}" alt="Игра 1">
            <a href="game-quest">Квест</a>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_ROULETTE}" alt="Игра 2">
            <a href="game-roulette">Рулетка</a>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_QUIZ}" alt="Игра 3">
            <a href="game-quiz">Викторина</a>
        </div>
    </div>
</main>

<%@include file="footer.jsp" %>

<style>
    /* Блок для бегущей строки */
    .marquee {
        width: 100%;
        overflow: hidden;
        white-space: nowrap;
        background-color: #333333; /* Чёрный фон для контраста */
        padding: 10px 0; /* Отступы сверху и снизу */
    }

    /* Стили текста */
    .marquee h1 {
        display: inline-block;
        font-size: 2em; /* Размер текста */
        color: white; /* Белый цвет текста */
        animation: marquee 10s linear infinite; /* Анимация текста */
        text-transform: uppercase; /* Преобразование текста в верхний регистр */
    }

    /* Ключевые кадры для анимации */
    @keyframes marquee {
        0% {
            transform: translateX(100%);
        }
        100% {
            transform: translateX(-100%);
        }
    }
</style>
