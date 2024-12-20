<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>

<c:choose>
    <c:when test="${isWin == true}">
        <div class="container1">
            <!-- Верхняя часть: картинка, занимающая две трети пространства -->
            <div class="image-container1">
                <img src="${imageUrl}" alt="Картинка" class="top-image1">
            </div>

            <!-- Средняя часть: текст -->
            <div class="text-container1">
                <p style="white-space: pre-wrap;">${description}</p>
            </div>

            <!-- Нижняя часть: кнопка -->
            <div class="button-container1">
                <form action="start-page" method="post">
                    <button type="submit" class="btn" style="white-space: pre-wrap;">ОК</button>
                </form>
            </div>
        </div>
    </c:when>
    <c:when test="${isLoss == true}">
        <div class="container1">
            <!-- Верхняя часть: картинка, занимающая две трети пространства -->
            <div class="image-container1">
                <img src="${imageUrl}" alt="Картинка" class="top-image1">
            </div>

            <!-- Средняя часть: текст -->
            <div class="text-container">
                <p style="white-space: pre-wrap;">${lossСause}</p>
                <p style="white-space: pre-wrap;">${description}</p>
            </div>

            <!-- Нижняя часть: кнопка -->
            <div class="button-container1">
                <form action="start-page" method="post">
                    <button type="submit" class="btn" style="white-space: pre-wrap;">ОК</button>
                </form>
            </div>
        </div>
    </c:when>
    <c:when test="${isWin != true and isLoss != true}">

        <div class="container">
            <!-- Левая часть: картинка -->
            <div class="left-part">
                <img src="${imageUrl}" alt="Картинка">
            </div>

            <!-- Правая часть: блок для дальнейшей верстки -->
            <div class="right-part">
                <!-- Верхняя часть: текст -->
                <div class="top-part">
                    <p style="white-space: pre-wrap;">${result}</p>
                    <p style="white-space: pre-wrap;">${description}</p>
                </div>

                <!-- Нижняя часть: кнопки и ресурсы -->
                <div class="bottom-part">
                    <!-- Кнопки -->
                    <div class="buttons">
                        <!-- Левая кнопка -->
                        <form id="authFormLeft" action="/game-quest" method="get">
                            <button style="white-space: pre-wrap;" type="submit" name="pickedButton" value="l"
                                    class="btn">${buttonLeft}</button>
                        </form>
                        <!-- Правая кнопка -->
                        <form id="authFormRight" action="/game-quest" method="get">
                            <button style="white-space: pre-wrap;" type="submit" name="pickedButton" value="r"
                                    class="btn">${buttonRight}</button>
                        </form>

                    </div>

                    <!-- Ресурсы -->
                    <div class="resources">
                        <!-- Время -->

                        <div class="resource">
                            <img src="${sessionScope.IMAGE_URL_TIME}" alt="Время">
                            <span>${time}</span>
                        </div>

                        <!-- Деньги -->
                        <div class="resource">
                            <img src="${sessionScope.IMAGE_URL_EVIDENCE}" alt="Улики">
                            <span>${evidence}</span>
                        </div>

                        <!-- Улики -->
                        <div class="resource">
                            <img src="${sessionScope.IMAGE_URL_GOLD}" alt="Деньги">
                            <span>${gold}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </c:when>
</c:choose>


<style>
    /* Основные стили */


    .resource span {
        font-size: 2em; /* Увеличенный текст в 2 раза */
    }

    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
        font-family: "Orbitron", sans-serif; /* Шрифт */
        background-color: #333333; /* Задний фон */
        color: white; /* Белый текст */
    }

    .container {
        display: flex;
        height: 100vh; /* Контейнер занимает всю высоту экрана */
    }

    .left-part {
        flex: 1; /* Левая часть занимает половину ширины */
    }

    .left-part img {
        width: 100%;
        height: 100%;
        object-fit: cover; /* Картинка заполняет весь блок */
    }

    .right-part {
        flex: 1; /* Правая часть занимает половину ширины */
        display: flex;
        flex-direction: column; /* Разделение на верхнюю и нижнюю части */
        padding: 20px; /* Отступы внутри блока */
    }

    .top-part {
        flex: 1; /* Верхняя часть занимает половину высоты правой части */
        display: block; /* Строки будут идти одна под другой */
    }

    .bottom-part {
        flex: 1; /* Нижняя часть занимает половину высоты правой части */
        display: flex;
        flex-direction: column; /* Разделение на кнопки и ресурсы */
        margin-top: 20px; /* Отступ между верхней и нижней частями */
    }

    .buttons {
        display: flex;
        justify-content: space-between; /* Растягиваем кнопки по горизонтали */
    }

    .btn {
        flex: 1; /* Кнопки растягиваются по горизонтали */
        margin: 0 10px; /* Отступы между кнопками */
        padding: 10px;
        text-align: center;
        background-color: black; /* Цвет кнопок */
        color: white; /* Белый текст на кнопках */
        border: none;
        cursor: pointer;
    }

    .resources {
        display: flex;
        justify-content: space-between; /* Расположение ресурсов по горизонтали */
        margin-top: 20px; /* Отступ между кнопками и ресурсами */
    }

    .resource {
        display: flex;
        align-items: center; /* Выравнивание иконки и текста по вертикали */
    }

    .resource img {
        width: 100px;
        height: 100px;
        margin-right: 20px; /* Отступ между иконкой и текстом */
    }
</style>

<%--стили для вин,луз--%>
<style>
    .container1 {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh; /* Занимает всю высоту экрана */
        text-align: center;
    }

    .image-container1 {
        width: 100%;
        height: 66.66%; /* Две трети высоты */
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden; /* Чтобы изображение не выходило за границы */
    }

    .top-image1 {
        max-width: 100%;
        max-height: 100%;
        object-fit: contain; /* Подгоняет изображение под размеры контейнера */
    }

    .text-container1 {
        width: 100%;
        height: 20%; /* Оставшаяся часть под текстом */
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 10px;
    }

    .button-container1 {
        width: 100%;
        height: 13.34%; /* Оставшаяся часть под кнопкой */
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .ok-button1 {
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
    }
</style>
<%@include file="footer.jsp" %>