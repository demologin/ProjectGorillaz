package com.javarush.shakhurov;

import com.javarush.shakhurov.controller.UserController;
import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.model.game.Game;
import com.javarush.shakhurov.repository.UserRepository;
import com.javarush.shakhurov.service.GameService;
import com.javarush.shakhurov.service.UserService;
import com.javarush.shakhurov.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    Javalin app;
    private static MockWebServer mockBackEnd;
    private static Context ctx;

    @BeforeAll
    static void setUpMock() throws IOException {
        mockBackEnd = new MockWebServer();
        var html = Files.readString(Paths.get("src/test/resources/pageForTest.html"));
        var serverResponse = new MockResponse()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setResponseCode(200)
                .setBody(html);
        mockBackEnd.enqueue(serverResponse);
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    public final void setUp() throws Exception {
        app = App.getApp();
        ctx = mock(Context.class);
    }

    @Test
    @DisplayName("Index method returns list of all users with OK status")
    public void indexReturnsAllUsersWithOkStatusTest() throws SQLException {

        List<User> users = List.of(new User("John Doe", "john@example.com", "password", "user"));
        try (MockedStatic<UserService> userServiceMock = mockStatic(UserService.class)) {
            userServiceMock.when(() -> UserService.getAll()).thenReturn(users);

            UserController.index(ctx);

            verify(ctx).status(HttpStatus.OK);
            verify(ctx).render(eq("users/index.jte"), anyMap());
        }
    }

    @Test
    @DisplayName("Page user show no authorization")
    public void pageUserShowNoAuthorizationTest() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users/1");

            assertEquals(401, response.code());
        });
    }

    @Test
    @DisplayName("Correctly sets HTTP status to OK when user is found")
    public void showSetsHttpStatusOkWhenUserFoundTest() throws SQLException {

        Long userId = 8L;

        User user = new User("John", "john@example.com", "password", "user");
        user.setId(userId);
        List<Game> games = new ArrayList<>();

        try (MockedStatic<UserService> userServiceMock = mockStatic(UserService.class);
             MockedStatic<GameService> gameServiceMock = mockStatic(GameService.class);) {
            when(ctx.pathParam("id")).thenReturn(userId.toString());
            userServiceMock.when(() -> UserService.findById(userId)).thenReturn(Optional.of(user));
            gameServiceMock.when(() -> GameService.getAllGameForUser(userId)).thenReturn(games);

            UserController.show(ctx);

            verify(ctx).status(HttpStatus.OK);
        }
    }

    @Test
    @DisplayName("Show user no found")
    public void showUserNoFoundTest() {
        when(ctx.pathParam("id")).thenReturn("9999999");

        assertThrows(NotFoundResponse.class, () -> {
            UserController.show(ctx);
        });

        verify(ctx, never()).render(anyString(), any());
        verify(ctx, never()).status(any());
    }

    @Test
    @DisplayName("create user success")
    public void createUserSuccessTest() throws SQLException {
        User userExpected = new User("Ivan", "ivan@gmail.com", "wqerty", "user");
        Long id = UserRepository.save(userExpected);
        JavalinTest.test(app, (server, client) -> {
            client.post(NamedRoutes.startPath());

            var userActual = UserRepository.findById(id).get();
            assertEquals(userExpected.getName(), userActual.getName());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", "ser"})
    public void createUserWithWrongNameTest(String  name) throws SQLException {
        when(ctx.formParam("name")).thenReturn(name);
        when(ctx.formParam("email")).thenReturn("test@nail.com");
        when(ctx.formParam("password")).thenReturn("password");
        when(ctx.formParam("role")).thenReturn("user");

        UserController.create(ctx);

        verify(ctx).status(HttpStatus.BAD_REQUEST);
        verify(ctx).sessionAttribute("flash", "Неккоректные данные");
        verify(ctx).redirect(NamedRoutes.registrationPath());
    }

    @ParameterizedTest
    @NullSource
    public void createUserWithNullNameTest(String  name) throws SQLException {
        when(ctx.formParam("name")).thenReturn(name);
        when(ctx.formParam("email")).thenReturn("test@nail.com");
        when(ctx.formParam("password")).thenReturn("password");
        when(ctx.formParam("role")).thenReturn("user");

        UserController.create(ctx);

        verify(ctx).status(HttpStatus.BAD_REQUEST);
        verify(ctx).sessionAttribute("flash", "Неккоректные данные");
        verify(ctx).redirect(NamedRoutes.registrationPath());
    }

    @Test
    @DisplayName("User creation with valid name, email and password creates user and sets JWT cookie")
    public void validUserCreationSetsJwtCookie() throws SQLException {
        try (MockedStatic<UserService> userServiceMock = mockStatic(UserService.class)) {

            when(ctx.formParam("name")).thenReturn("validName");
            when(ctx.formParam("email")).thenReturn("user@test.com");
            when(ctx.formParam("password")).thenReturn("password123");
            when(ctx.formParam("role")).thenReturn("user");

            userServiceMock.when(() -> UserService.existByEmail("user@test.com")).thenReturn(true);
            userServiceMock.when(() -> UserService.create(any(User.class))).thenReturn(2L);

            UserController.create(ctx);

            verify(ctx).cookie(eq("jwt"), anyString());
            verify(ctx).cookie("userId", "2");
            verify(ctx).status(HttpStatus.CREATED);
            verify(ctx).redirect(NamedRoutes.startPath());
        }
    }

    @Test
    public void logoutTest() {

        UserController.logout(ctx);

        verify(ctx).sessionAttribute("flash", null);
        verify(ctx).cookie("jwt", "");
        verify(ctx).cookie("userId", "");
        verify(ctx).redirect(NamedRoutes.startPath());
    }

    @Test
    @DisplayName("User login with correct credentials sets JWT token and redirects to start page")
    public void userLoginSetsJwtAndRedirectsTest() throws SQLException {

        when(ctx.formParam("email")).thenReturn("admin@mail.com");
        when(ctx.formParam("password")).thenReturn("password");

        UserController.login(ctx);

        verify(ctx).cookie(eq("jwt"), anyString());
        verify(ctx).cookie("userId", "1");
        verify(ctx).status(HttpStatus.OK);
        verify(ctx).redirect(NamedRoutes.startPath());
    }

    @Test
    @DisplayName("Successfully deletes user and associated games when valid ID is provided")
    public void destroyUserAndGamesTest() throws SQLException {

        when(ctx.formParam("id")).thenReturn("1");

        UserController.destroy(ctx);

        verify(ctx).status(HttpStatus.OK);
        verify(ctx).redirect(NamedRoutes.usersPath());
    }
}
