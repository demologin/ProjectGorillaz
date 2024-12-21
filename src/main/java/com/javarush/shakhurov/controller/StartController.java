package com.javarush.shakhurov.controller;

import com.javarush.shakhurov.dto.BasePage;
import com.javarush.shakhurov.dto.UserPage;
import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.repository.UserRepository;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

public class StartController {

    public static void index(Context ctx) throws SQLException {
        String userId = ctx.cookie("userId");
        var id = userId != null && !userId.equals("") ? Long.parseLong(userId) : null;
        var user = id != null ? UserRepository.findById(id).orElse(null) : null;
        var page = new UserPage(user);

        BasePage.setUserInfo(addUserInfo(user));

        page.setFlash(ctx.consumeSessionAttribute("flash"));

        //var login = ctx.cookie("jwt");
        //BasePage.setLogin(login == null || login.equals("") ? false : true);

        ctx.render("start.jte", model("page", page));
    }

    private static Map<String, String> addUserInfo(User user) {
        return user == null ? new HashMap<>() : Map.of("id", String.valueOf(user.getId()),
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole());
    }
}
