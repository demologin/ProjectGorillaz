@import com.javarush.shakhurov.dto.UserPage
@import com.javarush.shakhurov.dto.StartPage
@import com.javarush.shakhurov.utils.NamedRoutes
@param UserPage page


@template.layout.page(
content = @`
    <main class="flax-grow-1">
        @if(page.getFlash() != null)
            <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success" role="alert">
                <p class="m-0">${page.getFlash()}</p>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif
    <body>
    <section>
    <div class="container py-4 py-xl-5">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 mx-auto">
                <h1 class="col-md-8 col-xl-6 text-center mx-auto">QuestGame</h1>
                <p>${StartPage.getTitle()}</p>
            </div>
        </div>
        <div class="row gy-4 row-cols-1 row-cols-md-2 row-cols-xl-3">
            <div class="col">
                <div class="card"><img class="card-img-top w-100 d-block fit-cover" style="height: 200px;" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png">
                    <div class="card-body p-4">
                        <h4 class="card-title">Игра "Калькулятор"</h4>
                        <p class="card-text">В данной игре для двух чисел нужно произвести математическую операцию "+", "-", "*" или "/" и дать верный ответ.</p>
                        <div class="d-flex">
                            <div></div>
                        </div>
                        @if(page.getUser() != null)
                            <form action="${NamedRoutes.startPath()}" method="post" name="CalcGame" class="rss-form text-body">
                                <button class="btn btn-primary" type="submit" name="game" value="CalcGame">Играть</button>
                            </form>
                        @else
                            <p>чтобы играть нужна авторизация</p>
                        @endif
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card"><img class="card-img-top w-100 d-block fit-cover" style="height: 200px;" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png">
                    <div class="card-body p-4">
                        <h4 class="card-title">Игра "Чётное/не чётное"</h4>
                        <p class="card-text">В данной игре для числа нужно определить является ли оно чётным или не чйтным, ответив "да" или "нет"</p>
                        <div class="d-flex">
                            <div></div>
                        </div>
                        @if(page.getUser() != null)
                            <form action="${NamedRoutes.startPath()}" method="post" name="EvenGame" class="rss-form text-body">
                                <button class="btn btn-primary" type="submit" name="game" value="EvenGame">Играть</button>
                            </form>
                        @else
                            <p>чтобы играть нужна авторизация</p>
                        @endif
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card"><img class="card-img-top w-100 d-block fit-cover" style="height: 200px;" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png">
                    <div class="card-body p-4">
                        <h4 class="card-title">Игра "Прогрессия"</h4>
                        <p class="card-text">В данной игре предоставляется прогрессия с пропущенным числом нужно ответить какое число пропущено.</p>
                        <div class="d-flex">
                            <div></div>
                        </div>
                        @if(page.getUser() != null)
                            <form action="${NamedRoutes.startPath()}" method="post" name="ProgressionGame" class="rss-form text-body">
                                <button class="btn btn-primary" type="submit" name="game" value="ProgressionGame">Играть</button>
                            </form>
                        @else
                            <p>чтобы играть нужна авторизация</p>
                        @endif
                </div>
            </div>
        </div>
    </div>
    </div>
    </section>
    </body>
    </main>
`
)