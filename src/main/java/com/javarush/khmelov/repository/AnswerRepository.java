package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Answer;

import java.util.stream.Stream;

public class AnswerRepository extends BaseRepository<Answer> {

    @Override
    public Stream<Answer> find(Answer pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestionId(), u.getQuestionId()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()))
                .filter(u -> nullOrEquals(pattern.getNextQuestionId(), u.getNextQuestionId()));
    }

}
