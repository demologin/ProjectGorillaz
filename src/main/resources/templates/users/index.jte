@import com.javarush.shakhurov.dto.UsersPage
@import com.javarush.shakhurov.utils.NamedRoutes
@param UsersPage page

@template.layout.page(
content = @`
    <main class="flex-grow-1">
        <section>
            <div class="container-lg mt-5">
                @if(page.getUsers().isEmpty())
                    <p>Пока не добавлено ни одного пользователя</p>
                @endif
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Role</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    @for(var user : page.getUsers())
                        <tbody>
                        <tr>
                            <th scope="row">${user.getId()}</th>
                            <td><a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a></td>
                            <th scope="row">${user.getEmail()}</th>
                            <th scope="row">${user.getRole()}</th>
                            <th scope="row">
                                <form action="${NamedRoutes.usersPath()}" method="post" class="rss-form text-body">
                                <button type="submit" name="id" value="${user.getId()}"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                    <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                                    </svg></button></form></th>
                        </tr>
                        </tbody>
                    @endfor
                </table>
            </div>
        </section>
    </main>
`
)