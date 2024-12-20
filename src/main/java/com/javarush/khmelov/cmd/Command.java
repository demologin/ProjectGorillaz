package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_NO_ARGS;

public interface Command {

    private static String convertCamelCaseToKebabStyle(String string) {
        String snakeName = string.chars()
                .mapToObj(s -> String.valueOf((char) s))
                .flatMap(s -> s.matches("[A-Z]")
                        ? Stream.of("-", s)
                        : Stream.of(s))
                .collect(Collectors.joining())
                .toLowerCase();
        return snakeName.startsWith("-")
                ? snakeName.substring(1)
                : snakeName;
    }

    default String doGet(HttpServletRequest request) {
        return getView();
    }

    default String doPost(HttpServletRequest request) {
        return getView();
    }

    default String getView() {
        String simpleName = this.getClass().getSimpleName();
        return convertCamelCaseToKebabStyle(simpleName);
    }

    default void addUserInfoToSession(HttpServletRequest req, User user) {
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("login", user.getLogin());
        req.getSession().setAttribute("gamescount", user.getGamesCount());
        req.getSession().setAttribute("winscount", user.getWinsCount());
        req.getSession().setAttribute("losscount", user.getLossCount());
    }

    default User findUser(String login, UserService userService) {
        Collection<User> allUsers = userService.getAll();
        for (User u : allUsers) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    default boolean isEmptyArg(HttpServletRequest req, String arg) {
        if (arg.equals("")) {
            RequestHelpers.createError(req, ERROR_NO_ARGS);
            return true;
        }
        return false;
    }

    default void addUserLoss(HttpServletRequest req, UserService userService) {
        if (islogged(req)) {
            User user = findUser(req.getSession().getAttribute("login").toString(), userService);
            user.setGamesCount(user.getGamesCount() + 1);
            user.setLossCount(user.getLossCount() + 1);
            addUserInfoToSession(req, user);
        }
    }

    default void addUserWin(HttpServletRequest req, UserService userService) {
        if (islogged(req)) {
            User user = findUser(req.getSession().getAttribute("login").toString(), userService);
            user.setGamesCount(user.getGamesCount() + 1);
            user.setWinsCount(user.getWinsCount() + 1);
            addUserInfoToSession(req, user);
        }
    }

    private boolean islogged(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("login") != null;
    }

}
