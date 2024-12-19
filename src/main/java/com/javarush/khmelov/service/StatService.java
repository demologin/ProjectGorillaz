package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Game;
import com.javarush.khmelov.entity.GameState;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.entity.UserStatistics;
import com.javarush.khmelov.repository.GameRepository;
import com.javarush.khmelov.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class StatService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public Collection<UserStatistics> getUserStatistics() {
        return userRepository.getAll()
                .stream()
                .map(this::calculateUserStatistics)
                .toList();
    }

    public UserStatistics getTotalUserStatistics() {
        UserStatistics all = UserStatistics.builder().login("ALL").build();
        for (UserStatistics userStatistic : getUserStatistics()) {
            all.setPlay(all.getPlay() + userStatistic.getPlay());
            all.setTotal(all.getTotal() + userStatistic.getTotal());
            all.setWin(all.getWin() + userStatistic.getWin());
            all.setLost(all.getLost() + userStatistic.getLost());
        }
        return all;
    }

    private UserStatistics calculateUserStatistics(User user) {
        Game pattern = Game.builder().userId(user.getId()).build();
        List<Game> games = gameRepository.find(pattern).toList();
        long win = games.stream().filter(game -> game.getGameState().equals(GameState.WIN)).count();
        long lost = games.stream().filter(game -> game.getGameState().equals(GameState.LOST)).count();
        long play = games.stream().filter(game -> game.getGameState().equals(GameState.PLAY)).count();
        return UserStatistics.builder()
                .login(user.getLogin())
                .win(win)
                .lost(lost)
                .play(play)
                .total(win + lost + play)
                .build();
    }

}
