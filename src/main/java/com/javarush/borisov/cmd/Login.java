package com.javarush.borisov.cmd;

import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.Db;
import com.javarush.borisov.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Login implements Command {
    @Override
    public String doGet(HttpServletRequest req) {

        Db db = ClassCreator.get(Db.class);
        for (User user : db.getUsers()) {
            if (user.getMail().equals(req.getParameter("email")) && user.getPassword().equals(req.getParameter("password"))) {
                HttpSession session = req.getSession(false);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60);

               return "/start-page";
            }
        }

       return getView() ;
    }
}
