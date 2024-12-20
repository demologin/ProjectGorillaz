<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 11.12.2024
  Time: 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Нижняя часть -->
<footer class="footer">
    <div style="display: flex; align-items: center; justify-content: center; width: 100%; height: 100%;">
        <p style="margin: 0; text-align: center;">Санкт-Петербург 2024г</p>
    </div>
    <c:choose>
        <c:when test="${not empty sessionScope.login}">
            <div style="margin: 20px; text-align: center;">
                <!-- Аудиоплеер -->
                <audio id="audioPlayer" controls style="width: 300px;">
                    <source src="${sessionScope.AUDIO_START_FOOTER}" type="audio/mpeg">
                    Ваш браузер не поддерживает аудио.
                </audio>
                <!-- Регулировка громкости -->
                <div style="margin-top: 10px;">
                    <label for="volumeControl">Громкость:</label>
                    <input id="volumeControl" type="range" min="0" max="1" step="0.01" value="0.5"
                           onchange="setVolume(this.value)"/>
                </div>
            </div>
        </c:when>
    </c:choose>
</footer>
<script>
    const audioPlayer = document.getElementById('audioPlayer');

    // Устанавливаем громкость через ползунок
    function setVolume(value) {
        audioPlayer.volume = value;
        localStorage.setItem('audioVolume', value); // Сохраняем громкость в localStorage
    }

    // Восстанавливаем состояние плеера
    window.onload = function () {
        const savedVolume = localStorage.getItem('audioVolume');
        if (savedVolume !== null) {
            audioPlayer.volume = parseFloat(savedVolume); // Восстанавливаем громкость
            document.getElementById('volumeControl').value = savedVolume; // Обновляем ползунок
        }

        const savedTime = localStorage.getItem('audioTime');
        if (savedTime !== null) {
            audioPlayer.currentTime = parseFloat(savedTime); // Восстанавливаем текущее время
        }

        // Продолжаем воспроизведение, если оно было до перезагрузки
        if (localStorage.getItem('audioPlaying') === 'true') {
            audioPlayer.play();
        }
    };

    // Сохраняем состояние плеера перед перезагрузкой страницы
    window.onbeforeunload = function () {
        localStorage.setItem('audioVolume', audioPlayer.volume); // Сохраняем громкость
        localStorage.setItem('audioTime', audioPlayer.currentTime); // Сохраняем текущее время
        localStorage.setItem('audioPlaying', !audioPlayer.paused); // Сохраняем статус воспроизведения
    };
</script>
<script>
    function submitForm(action) {
        // Устанавливаем action формы в зависимости от нажатой кнопки
        document.getElementById("authForm").action = action;
        // Отправляем форму
        document.getElementById("authForm").submit();
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>