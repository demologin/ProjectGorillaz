<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18.12.2024
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Аудиоплеер</title>
</head>
<body>
<audio id="audioPlayer" controls autoplay style="width: 300px;">
    <source src="${sessionScope.AUDIO_START_FOOTER}" type="audio/mpeg">
    Ваш браузер не поддерживает аудио.
</audio>
<script>
    const audioPlayer = document.getElementById('audioPlayer');

    // Восстанавливаем громкость из localStorage
    const savedVolume = localStorage.getItem('audioVolume');
    if (savedVolume) {
        audioPlayer.volume = savedVolume;
    }

    // Сохраняем громкость при её изменении
    audioPlayer.addEventListener('volumechange', () => {
        localStorage.setItem('audioVolume', audioPlayer.volume);
    });

    // Сохраняем текущую позицию воспроизведения
    audioPlayer.addEventListener('timeupdate', () => {
        localStorage.setItem('audioTime', audioPlayer.currentTime);
    });

    // Восстанавливаем позицию при загрузке
    audioPlayer.addEventListener('loadedmetadata', () => {
        const savedTime = localStorage.getItem('audioTime');
        if (savedTime) {
            audioPlayer.currentTime = savedTime;
        }
    });
</script>
</body>
</html>