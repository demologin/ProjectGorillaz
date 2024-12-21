package com.javarush.borisov.cmd;

import jakarta.servlet.http.HttpServletRequest;

public class StartPage implements Command {

    @Override
    public String doGet(HttpServletRequest req) {
        if (req.getParameter("invalidate")!= null){
            req.getSession().removeAttribute("user");
            req.getSession().invalidate();
        }
        return getView();
    }
}
