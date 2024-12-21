package com.javarush.shakhurov;

import com.javarush.shakhurov.config.Provider;
import com.javarush.shakhurov.controller.*;
import com.javarush.shakhurov.model.Roles;
import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.repository.BaseRepository;
import com.javarush.shakhurov.repository.UserRepository;
import com.javarush.shakhurov.utils.NamedRoutes;
import com.lambdaworks.crypto.SCryptUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.security.RouteRole;
import javalinjwt.JWTAccessManager;
import javalinjwt.JWTProvider;
import javalinjwt.JavalinJWT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {

        // run app
        Javalin app = getApp();
        // run web server
        app.start(8080);
    }

    public static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        return TemplateEngine.create(codeResolver, ContentType.Html);
    }

    public static Javalin getApp() throws Exception {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        var dataSource = new HikariDataSource(hikariConfig);
        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
            config.bundledPlugins.enableDevLogging();
        });

        // add "admin" pre run app
        UserRepository.save(new User("Admin", "admin@mail.com", SCryptUtil.scrypt("password", 2, 2, 2), "admin"));

        // create the provider
        JWTProvider<User> provider = Provider.create();

        Handler decodeHandler = JavalinJWT.createCookieDecodeHandler(provider);
        // create the access manager
        Map<String, RouteRole> rolesMapping = new HashMap<>() {{
            put("user", Roles.USER);
            put("admin", Roles.ADMIN);
        }};

        JWTAccessManager accessManager = new JWTAccessManager("role", rolesMapping, Roles.GUEST);

        // set the paths
        app.before(decodeHandler);
        app.beforeMatched(accessManager);

        app.get(NamedRoutes.startPath(), StartController::index, Roles.GUEST, Roles.USER, Roles.ADMIN);

        app.post(NamedRoutes.startPath(), GameController::create, Roles.USER, Roles.ADMIN);
        app.get(NamedRoutes.gamePath("{id}"), GameController::show, Roles.USER, Roles.ADMIN);
        app.post(NamedRoutes.gamePath("{id}"), GameController::show, Roles.USER, Roles.ADMIN);
        app.post(NamedRoutes.userPath("{id}"), GameController::destroy, Roles.USER, Roles.ADMIN);

        app.get(NamedRoutes.userPath("{id}"), UserController::show, Roles.USER, Roles.ADMIN);
        app.post(NamedRoutes.registrationPath(), UserController::create, Roles.GUEST, Roles.USER, Roles.ADMIN);
        app.post(NamedRoutes.loginPath(), UserController::login, Roles.GUEST, Roles.USER, Roles.ADMIN);
        app.get(NamedRoutes.logoutPath(), UserController::logout, Roles.USER, Roles.ADMIN);
        app.get(NamedRoutes.usersPath(), UserController::index, Roles.ADMIN);
        app.post(NamedRoutes.usersPath(), UserController::destroy, Roles.ADMIN);

        app.get(NamedRoutes.statisticPath(), StatisticController::index, Roles.GUEST, Roles.USER, Roles.ADMIN);

        app.get(NamedRoutes.registrationPath(), RegistrationController::index, Roles.GUEST, Roles.USER, Roles.ADMIN);

        app.get(NamedRoutes.loginPath(), LoginController::index, Roles.GUEST, Roles.USER, Roles.ADMIN);

        return app;
    }
}
