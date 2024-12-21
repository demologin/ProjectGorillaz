package com.javarush.shakhurov.controller;

import com.javarush.shakhurov.config.Provider;
import com.javarush.shakhurov.dto.UserPage;
import com.javarush.shakhurov.dto.UsersPage;
import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.service.GameService;
import com.javarush.shakhurov.service.UserService;
import com.javarush.shakhurov.utils.NamedRoutes;
import com.lambdaworks.crypto.SCryptUtil;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import javalinjwt.JWTProvider;

import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UserController {
    private static final JWTProvider<User> provider = Provider.create();

    public static void index(Context ctx) {
        var users = UserService.getAll();
        var page = new UsersPage(users);
        ctx.status(HttpStatus.OK);
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = Long.parseLong(ctx.pathParam("id"));
        var user = UserService.findById(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
        var games = GameService.getAllGameForUser(id);
        var page = new UserPage(user, games);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.status(HttpStatus.OK);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        var level = ctx.formParam("role") == null ? "user" : ctx.formParam("role");

        if (isValidName(name)
                && isValidEmail(email)
                && isValidPassword(password)
                && UserService.existByEmail(email)) {
            var passwordHash = SCryptUtil.scrypt(password, 2, 2, 2);
            var user = new User(name, email, passwordHash, level);
            var id = UserService.create(user);

            var token = provider.generateToken(user);

            ctx.cookie("jwt", token);
            ctx.cookie("userId", String.valueOf(id));

            ctx.sessionAttribute("flash", "Игрок создан");
            ctx.status(HttpStatus.CREATED);
            ctx.redirect(NamedRoutes.startPath());
        } else if (!UserService.existByEmail(email)) {
            ctx.sessionAttribute("flash", "Игрок с таким " + email + " уже существует");
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.redirect(NamedRoutes.registrationPath());
        } else {
            ctx.sessionAttribute("flash", "Неккоректные данные");
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.redirect(NamedRoutes.registrationPath());
        }
    }

    public static void login(Context ctx) {
        try {
            var email = ctx.formParam("email");
            var password = ctx.formParam("password");
            var user = UserService.findByEmail(email)
                    .orElseThrow(() -> new NotFoundResponse("User with email = " + email + " not found"));
            if (user.getPassword() != null && SCryptUtil.check(password, user.getPassword())) {

                var token = provider.generateToken(user);

                ctx.cookie("jwt", token);
                ctx.cookie("userId", String.valueOf(user.getId()));

                ctx.sessionAttribute("flash", "Привет " + user.getName() + " !");
                ctx.status(HttpStatus.OK);
                ctx.redirect(NamedRoutes.startPath());
            } else if (user.getEmail() == null) {
                ctx.sessionAttribute("flash", "Игрок с email - \"" + ctx.formParam("email") + "\" не существует");
                ctx.status(HttpStatus.BAD_REQUEST);
                ctx.redirect(NamedRoutes.loginPath());
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
                ctx.sessionAttribute("flash", "Некорректные логин или пароль");
                ctx.redirect(NamedRoutes.loginPath());
            }
        } catch (NotFoundResponse e) {
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.redirect(NamedRoutes.loginPath());
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("flash", null);
        ctx.cookie("jwt", "");
        ctx.cookie("userId", "");
        ctx.redirect(NamedRoutes.startPath());
    }

    public static void destroy(Context ctx) {
        var id = Long.parseLong(Objects.requireNonNull(ctx.formParam("id")));
        GameService.destroy(id);
        UserService.delete(id);
        ctx.status(HttpStatus.OK);
        ctx.redirect(NamedRoutes.usersPath());
    }

    private static boolean isValidName(String name) {
        return name != null && name.matches("(\\w+|[а-яА-Я0-9]+)") && name.length() >= 4;
    }

    private static boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return true;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.length() > 5;
    }
}
