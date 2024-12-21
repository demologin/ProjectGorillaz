package com.javarush.shakhurov.repository;

import com.javarush.shakhurov.model.FactoryGame;
import com.javarush.shakhurov.model.game.Game;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameRepository extends BaseRepository {

    public static Long save(Game game) throws SQLException {
        var sql = "INSERT INTO games (name, user_id, win, lose) VALUES (?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, game.getName());
            stmt.setLong(2, game.getUserId());
            stmt.setInt(3, 0);
            stmt.setInt(4, 0);
            stmt.executeUpdate();
            var generatedKey = stmt.getGeneratedKeys();
            if (generatedKey.next()) {
                game.setId(generatedKey.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
        return game.getId();
    }

    public static Optional<Game> findById(Long id) throws SQLException {
        var sql = "SELECT * FROM games WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var factoryGame = new FactoryGame();
                var name = resultSet.getString("name");
                var userId = resultSet.getLong("user_id");
                var countWin = resultSet.getInt("win");
                var countLose = resultSet.getInt("lose");
                var game = factoryGame.getGame(name);
                game.setId(id);
                game.setUserId(userId);
                game.setCountWin(countWin);
                game.setCountLose(countLose);
                return Optional.of(game);
            }
        }
        return Optional.empty();
    }

    public static void update(Game game) throws SQLException {
        var sql = "UPDATE games SET win = ?, lose = ? WHERE id = ?";
        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, game.getCountWin());
            stmt.setInt(2, game.getCountLose());
            stmt.setLong(3, game.getId());
            stmt.executeUpdate();
        }
    }

    public static List<Game> getAllGameForUser(Long id) throws SQLException {
        var sql = "SELECT name, user_id, SUM (win) AS count_win, SUM (lose) AS count_lose "
                + "FROM games WHERE user_id = ? "
                + "GROUP BY name, user_id";
        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            List<Game> games = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long userId = resultSet.getLong("user_id");
                int countWin = resultSet.getInt("count_win");
                int countLose = resultSet.getInt("count_lose");

                FactoryGame factoryGame = new FactoryGame();
                Game game = factoryGame.getGame(name);
                game.setUserId(userId);
                game.setCountWin(countWin);
                game.setCountLose(countLose);

                games.add(game);
            }
            return games;
        }
    }

    public static void deleteAllGameForUser(Long id) throws SQLException {
        var sql = "DELETE FROM games WHERE user_id = ?";
        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Map<String, Game>> getAllUserNameWithGames() throws SQLException {
        var sql = "SELECT u.name, g.name, g.user_id, SUM (g.win) AS count_win, SUM (g.lose) AS count_lose "
                + "FROM games AS g "
                + "INNER JOIN users AS u "
                + "ON g.user_id = u.id "
                + "GROUP BY u.name, g.user_id, g.name";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            List<Map<String,Game>> games = new ArrayList<>();
            while (resultSet.next()) {
                String userName = resultSet.getString("users.name");
                String gameName = resultSet.getString("games.name");
                Long userId = resultSet.getLong("user_id");
                int countWin = resultSet.getInt("count_win");
                int countLose = resultSet.getInt("count_lose");

                FactoryGame factoryGame = new FactoryGame();
                Game game = factoryGame.getGame(gameName);
                game.setUserId(userId);
                game.setCountWin(countWin);
                game.setCountLose(countLose);

                games.add(Map.of(userName, game));
            }
            return games;
        }
    }
}
