package com.javarush.shakhurov.controller;

import com.javarush.shakhurov.dto.BasePage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class LoginController {

    public static void index(Context ctx) {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("users/login.jte", model("page", page));
    }
}
