package com.javarush.khmelov.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_MESSAGE;

@WebFilter({"", "/home", "/start-page", "/game-quest", "/game-quiz",
        "/game-roulette", "/login-entrance", "/login-registration"})
public class ErrorCleanerFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(req, res);
        HttpSession session = req.getSession(false);
        if (req.getMethod().equals("GET") && session != null) {
            session.removeAttribute(ERROR_MESSAGE);
        }
    }
}
