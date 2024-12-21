package com.javarush.shakhurov.controller;

import com.javarush.shakhurov.dto.StatisticPage;
import com.javarush.shakhurov.service.GameService;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class StatisticController {

    public static void index(Context ctx) {
        var usersWithGames = GameService.getAllUserNameWithGames();
        var page = new StatisticPage(usersWithGames);
        ctx.render("statistic/index.jte", model("page", page)).status(200);
    }
}
