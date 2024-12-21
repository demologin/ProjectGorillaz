@import com.javarush.shakhurov.dto.UserPage
@import com.javarush.shakhurov.utils.NamedRoutes
@param UserPage page

@template.layout.page(
content = @`
    <main class="flax-grow-1">
    <section>
        <div class="container-lg mt-5">
            <h1>${page.getUser().getName()}</h1>
            <p>Статистика твоих игр</p>
            @if (page.getGames().isEmpty())
                <p>Ни одна из игр ещё не сыграна.</p>
            @else
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                    <tr>
                        <th scope="col">Название игры</th>
                        <th scope="col">Количество побед</th>
                        <th scope="col">Количество поражений</th>
                    </tr>
                    </thead>
                    @for(var game : page.getGames())
                        <tbody>
                        <tr>
                            <th scope="row">${game.getName()}</th>
                            <th scope="row">${game.getCountWin()}</th>
                            <th scope="row">${game.getCountLose()}</th>
                        </tr>
                        </tbody>
                    @endfor
                </table>
                <form action="${NamedRoutes.userPath(page.getUser().getId())}" method="post" class="rss-form text-body">
                    <button type="submit" class="btn btn-danger">Удалить все игры</button></form>
            @endif
        </div>
    </section>
    </main>
`
)