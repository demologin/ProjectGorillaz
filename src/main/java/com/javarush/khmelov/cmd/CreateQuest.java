package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Key;
import com.javarush.khmelov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class CreateQuest implements Command {

    @Override
    public String doPost(HttpServletRequest request) {
        String name = request.getParameter(Key.NAME);
        String text = request.getParameter(Key.TEXT);
        Optional<User> optionalUser = RequestHelpers.getUser(request.getSession());
        //optionalUser.ifPresent(user -> questService.create(name, text, user.getId()));
        return Go.HOME;
    }
}
