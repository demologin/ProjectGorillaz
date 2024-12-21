package com.javarush.borisov.filter;

import com.javarush.borisov.constants.UserRules;
import com.javarush.borisov.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter({"/start-page", "/requests-list", "/login" })
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.equals("/") ? "/start-page" : requestURI;
        requestURI = requestURI.replace(requestURI.substring(0, requestURI.indexOf('/') ),"");
        HttpSession session = req.getSession();

        if(session.getAttribute("user") == null) {
            session.setAttribute("user", new User("guest", null, null, UserRules.GUEST));
            res.sendRedirect("/start-page");
        }else {
            boolean ok = false;
            User user = (User) req.getSession().getAttribute("user");
            List<String> permissions = user.getRole().getPermissions();
            for (String permission : permissions) {
                if (requestURI.equals(permission)) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                super.doFilter(req, res, chain);
            } else {
                res.sendRedirect("/start-page");
            }
        }


    }
}
