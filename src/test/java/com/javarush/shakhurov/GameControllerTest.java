package com.javarush.shakhurov;

import com.javarush.shakhurov.controller.GameController;
import com.javarush.shakhurov.dto.GamePage;
import com.javarush.shakhurov.model.FactoryGame;
import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.model.game.Game;
import com.javarush.shakhurov.repository.GameRepository;
import com.javarush.shakhurov.repository.UserRepository;
import com.javarush.shakhurov.service.GameService;
import com.javarush.shakhurov.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameControllerTest {
    Javalin app;
    private static MockWebServer mockBackEnd;
    private static Context ctx;
    private final FactoryGame factoryGame = new FactoryGame();

    @BeforeAll
    static void setUpMock() throws IOException {
        mockBackEnd = new MockWebServer();
        var html = Files.readString(Paths.get("src/test/resources/pageForTest.html"));
        var serverResponse = new MockResponse()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setResponseCode(HttpStatus.OK.getCode())
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
    @DisplayName("Show game state and process correct answer from user")
    public void showGameTest() throws SQLException {
        Context ctx = mock(Context.class);
        Game game = mock(Game.class);
        GamePage page = mock(GamePage.class);
        Optional<Game> optionalGame = Optional.of(game);

        try (MockedStatic<GameService> gameServiceMock = mockStatic(GameService.class)) {
            when(ctx.pathParam("id")).thenReturn("1");
            gameServiceMock.when(() -> GameService.findById(1L)).thenReturn(optionalGame);
            when(ctx.formParam("answer")).thenReturn("correctAnswer");
            when(game.getQuestionAndAnswer()).thenReturn(Map.of("question", "correctAnswer"));

            GameController.show(ctx);

            verify(ctx).sessionAttribute("flash", "Верно!");
            verify(ctx).render(eq("games/show.jte"), anyMap());
            verify(ctx).status(HttpStatus.OK);
        }
    }

    @Test
    @DisplayName("Show game with not authorization user")
    public void showGameWithNoAuthorizationTest() throws SQLException {
        User user = new User("Ivan", "ivan@gmail.com", "wqerty", "user");
        UserRepository.save(user);

        Game game = factoryGame.getGame("CalcGame");
        game.setUserId(user.getId());
        GameRepository.save(game);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/games/" + game.getId());

            assertEquals(401, response.code());
        });
    }

    @Test
    @DisplayName("Show game no found")
    public void showGameNoFoundTest() {
        when(ctx.pathParam("id")).thenReturn("9999999");

        assertThrows(NotFoundResponse.class, () -> {
            GameController.show(ctx);
        });

        verify(ctx, never()).render(anyString(), any());
        verify(ctx, never()).status(any());
    }

    @Test
    @DisplayName("Create game success")
    public void createGameSuccessTest() throws SQLException {
        User user = new User("Ivan", "ivan@gmail.com", "wqerty", "user");
        Long userId = UserRepository.save(user);

        Game gameExpected = new FactoryGame().getGame("CalcGame");
        gameExpected.setUserId(userId);
        Long gameId = GameRepository.save(gameExpected);

        JavalinTest.test(app, (server, client) -> {
            client.post("/users/" + userId);

            var gameActual = GameRepository.findById(gameId).get();
            assertEquals(gameExpected.getName(), gameActual.getName());
        });
    }

    @Test
    @DisplayName("Create new game with valid user ID and game name")
    public void createGameWithValidParamsTest() throws SQLException {

        when(ctx.formParam("game")).thenReturn("CalcGame");
        when(ctx.cookie("userId")).thenReturn("1");

        Game mockGame = mock(Game.class);
        when(mockGame.getName()).thenReturn("CalcGame");

        GameController.create(ctx);

        verify(ctx).status(HttpStatus.CREATED);
        verify(ctx).redirect(NamedRoutes.gamePath("1"));
    }

    @Test
    @DisplayName("Delete all games for a specific user")
    public void deleteAllGamesForUserTest() throws SQLException {
        Context ctx = mock(Context.class);
        Long userId = 1L;

        when(ctx.pathParam("id")).thenReturn(String.valueOf(userId));

        GameController.destroy(ctx);

        verify(ctx).status(HttpStatus.OK);
        verify(ctx).redirect(NamedRoutes.userPath(userId));
    }
}
