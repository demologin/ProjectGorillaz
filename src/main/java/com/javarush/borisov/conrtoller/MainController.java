package com.javarush.borisov.conrtoller;


import com.javarush.borisov.cmd.Command;
import com.javarush.borisov.cmd.HttpResolver;
import com.javarush.borisov.config.ClassCreator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Name;

import java.io.IOException;

@WebServlet({"", "/start-page", "/requests-list", "/login" })
public class MainController extends HttpServlet {

    private final HttpResolver httpResolver = ClassCreator.get(HttpResolver.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        String commandName = command.doGet(req);
        String fullJspPath = "/WEB-INF/" + commandName + ".jsp";
        req.getRequestDispatcher(fullJspPath).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Command command = httpResolver.resolve(req);
        String redirect = command.doPost(req);
        resp.sendRedirect(redirect);
    }
}
