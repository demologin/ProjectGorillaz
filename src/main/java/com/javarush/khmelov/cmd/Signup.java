package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings("unused")
@AllArgsConstructor
public class Signup implements Command {

    private final UserService userService;
    private final ImageService imageService;

    @Override
    @SneakyThrows
    public String doPost(HttpServletRequest request) {
        User user = User.builder()
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.USER)
                .build();
        userService.create(user);
        imageService.uploadImage(request, user.getImage());
        HttpSession session = request.getSession();
        session.setAttribute(Key.USER, user);
        return Go.PROFILE;
    }
}
