package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest implements AbstractEntity {
    private Long id;

    private String name;

    private String text;

    private Long authorId;

    private Long startQuestionId;

    private final Collection<Question> questions = new ArrayList<>();

}
