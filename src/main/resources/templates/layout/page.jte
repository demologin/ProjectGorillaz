@import com.javarush.shakhurov.dto.BasePage
@import gg.jte.Content
@import com.javarush.shakhurov.utils.NamedRoutes
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>QuestGame</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>

<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-dark navbar-expand-md bg-dark py-3">
    <div class="container"><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-6"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse flex-grow-0 order-md-first" id="navcol-6">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="${NamedRoutes.startPath()}">Главная</a></li>
                <li class="nav-item"><a class="nav-link" href="${NamedRoutes.statisticPath()}">Статистика игр</a></li>
                @if(!BasePage.getUserInfo().isEmpty() && BasePage.getUserInfo().get("role").equals("admin"))
                    <li class="nav-item"><a class="nav-link" href="${NamedRoutes.usersPath()}">Список игроков</a></li>
                @endif
            </ul>
        </div>
        @if(BasePage.getUserInfo().isEmpty())
            <div class="d-none d-md-block">
                <a class="btn btn-light me-2" role="button" href="${NamedRoutes.registrationPath()}">Регистрация</a>
                <a class="btn btn-primary" role="button" href="${NamedRoutes.loginPath()}">Войти</a>
            </div>
        @else
            <div class="d-none d-md-block">
                <a href="${NamedRoutes.userPath(BasePage.getUserInfo().get("id"))}">${BasePage.getUserInfo().get("name")}</a>
                <a class="btn btn-primary" role="button" href="${NamedRoutes.logoutPath()}">Выйти</a>
            </div>
        @endif
    </div>
</nav>
<main class="flex-grow-1">
    ${content}
</main>
<footer class="footer border-top py-3 mt-50 bg-dark text-white">
    <div class="container-xl">
        <div class="text-center">
            created by
            <a href="https://github.com/Denis-Shakhurov" target="_blank">Denis Shakhurov</a>
        </div>
    </div>
</footer>
</body>
</html>