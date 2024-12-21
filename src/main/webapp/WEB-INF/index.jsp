<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Главная страница</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<div class="container">
  <div class="quest-block">
    <h2>Космическое путешествие</h2>
    <img src="${pageContext.request.contextPath}/img/CosmicJourney/stage0.png" alt="Космическое путешествие">
    <form action="${pageContext.request.contextPath}/game" method="POST">
      <input type="hidden" name="questId" value="1">
      <input type="hidden" name="stageId" value="0">
      <button type="submit">Начать квест</button>
    </form>
  </div>
  <div class="quest-block">
    <h2>Опасность в Сосновом ручье</h2>
    <img src="${pageContext.request.contextPath}/img/PinebrookPeril/preview.png" alt="Опасность в Сосновом ручье">
    <form action="${pageContext.request.contextPath}/game" method="POST">
      <input type="hidden" name="questId" value="2">
      <input type="hidden" name="stageId" value="0">
      <button type="submit">Начать квест</button>
    </form>
  </div>
  <div class="quest-block">
    <h2>Последнее желание<br><br></h2>
    <img src="${pageContext.request.contextPath}/img/LastWish/preview.png" alt="Последнее желание">
    <form action="${pageContext.request.contextPath}/game" method="POST">
      <input type="hidden" name="questId" value="3">
      <input type="hidden" name="stageId" value="0">
      <button type="submit">Начать квест</button>
    </form>
  </div>
</div>
</body>
</html>
