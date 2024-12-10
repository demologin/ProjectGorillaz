package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;


@SuppressWarnings("unused")
@AllArgsConstructor
public class EditUser implements Command {

    private final UserService userService;
    private final ImageService imageService;

    @Override
    public String doGet(HttpServletRequest req) {
        String stringId = req.getParameter("id");
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            userService.get(id)
                    .ifPresent(user -> req.setAttribute("user", user));
        }
        return getView();
    }

    @Override
    @SneakyThrows
    public String doPost(HttpServletRequest req) {
        User user = User.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .role(Role.valueOf(req.getParameter("role")))
                .build();
        if (req.getParameter("create") != null) {
            userService.create(user);
        } else if (req.getParameter("update") != null) {
            user.setId(Long.parseLong(req.getParameter("id")));
            userService.update(user);
        }
        imageService.uploadImage(req, user.getImage());
        return getView() + "?id=" + user.getId();
    }
}