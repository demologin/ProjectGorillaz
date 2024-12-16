package com.javarush.khmelov.filter;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.RequestHelpers;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@WebFilter({
        Go.INDEX, Go.HOME, Go.SIGNUP, Go.LOGIN,
        Go.LOGOUT, Go.LIST_USER, Go.PROFILE, Go.EDIT_USER, Go.PLAY_GAME,
        Go.CREATE, Go.QUEST})
public class AuthorizationFilter extends HttpFilter {

    Map<Role, List<String>> permissions = Map.of(
            Role.GUEST, List.of(Go.HOME, Go.SIGNUP, Go.LOGIN),
            Role.USER, List.of(Go.HOME, Go.SIGNUP, Go.LOGIN,
                    Go.LOGOUT, Go.LIST_USER, Go.PROFILE, Go.EDIT_USER, Go.PLAY_GAME),
            Role.ADMIN, List.of(Go.HOME, Go.SIGNUP, Go.LOGIN,
                    Go.LOGOUT, Go.LIST_USER, Go.PROFILE, Go.EDIT_USER, Go.PLAY_GAME,
                    Go.CREATE, Go.QUEST)
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.equals("/") ? "/home" : requestURI;
        String cmdUri = "/" + requestURI.split("[?#/]")[1];
        HttpSession session = req.getSession();
        Role role = RequestHelpers.getUser(session)
                .map(User::getRole)
                .orElse(Role.GUEST);
        if (permissions.get(role).contains(cmdUri)) {
            chain.doFilter(req, res);
        } else {
            String message = "Access denied";
            log.warn(message);
            RequestHelpers.createError(req, message);
            res.sendRedirect(Go.LOGIN);
        }
    }
}
