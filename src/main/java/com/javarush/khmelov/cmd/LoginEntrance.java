package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_PASSWORD_OR_LOGIN_INCORRECT;
import static com.javarush.khmelov.storage.ConstantsCommon.GO_START;

public class LoginEntrance implements Command {
    private final UserService userService;
    String enteredLogin;
    String enteredPassword;

    public LoginEntrance(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req) {
        if (validTest(req)) {
            User user = findUser(enteredLogin, userService);
            addUserInfoToSession(req, user);
        }
        return GO_START;
    }

    private boolean validTest(HttpServletRequest req) {
        enteredLogin = req.getParameter("login");
        enteredPassword = req.getParameter("password");
        if (isEmptyArg(req, enteredLogin)) return false;
        if (isEmptyArg(req, enteredPassword)) return false;
        if (userService.loginOrPasswordIsIncorrect(enteredLogin, enteredPassword)) {
            RequestHelpers.createError(req, ERROR_PASSWORD_OR_LOGIN_INCORRECT);
            return false;
        }
        return true;
    }


}
