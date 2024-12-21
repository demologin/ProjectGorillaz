@import com.javarush.shakhurov.dto.StatisticPage
@param StatisticPage page

@template.layout.page(
content = @`
    <main class="flex-grow-1">
        <section>
            <div class="container-fluid bg-light p-5">
                <div class="row">
                    <div class="col-md-10 col-lg-8 mx-auto text-dark">
                        <h1 class="display-3 mb-0">Статистика игр</h1>
                        @if (page.getUsersWithGames().isEmpty())
                            <p>Ни одна из игр ещё не сыграна.</p>
                        @else
                            <table class="table table-bordered table-hover mt-3">
                            <thead>
                            <tr>
                                <th scope="col">id</th>
                                <th scope="col">Имя игрока</th>
                                <th scope="col">Название игры</th>
                                <th scope="col">Количество побед</th>
                                <th scope="col">Количество поражений</th>
                            </tr>
                            </thead>
                            @for(var map : page.getUsersWithGames())
                                @for(var userName : map.keySet())
                                    <tbody>
                                    <tr>
                                        <th scope="row">${map.get(userName).getUserId()}</th>
                                        <th scope="row">${userName}</th>
                                        <th scope="row">${map.get(userName).getName()}</th>
                                        <th scope="row">${map.get(userName).getCountWin()}</th>
                                        <th scope="row">${map.get(userName).getCountLose()}</th>
                                    </tr>
                                    </tbody>
                                @endfor
                            @endfor
                        </table>
                        @endif
                    </div>
                </div>
            </div>
        </section>
    </main>
`
)
