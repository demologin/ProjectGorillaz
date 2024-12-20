package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;

    private String login;

    private String password;

    private int gamesCount;

    private int winsCount;

    private int lossCount;

    public String getImage() { //TODO move to DTO
        return "image-" + id;
    }

}
