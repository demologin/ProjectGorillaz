package com.javarush.shakhurov.service;

import com.javarush.shakhurov.model.game.Game;
import com.javarush.shakhurov.repository.GameRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameService {

    public static Long create(Game game) {
        try {
            return GameRepository.save(game);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<Game> findById(Long id) {
        try {
            return GameRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Game> getAllGameForUser(Long userId) {
        try {
            return GameRepository.getAllGameForUser(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Game>> getAllUserNameWithGames() {
        try {
            return GameRepository.getAllUserNameWithGames();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Game game) {
        try {
            GameRepository.update(game);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void destroy(Long userId) {
        try {
            GameRepository.deleteAllGameForUser(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
