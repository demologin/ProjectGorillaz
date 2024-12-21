package com.javarush.borisov.entity;

import com.javarush.borisov.constants.UserRules;
import lombok.Getter;
import lombok.Setter;
// финализированные поля с сеттерами? стоит ли сеттеры прописать отдельно без аннотаций? погуглить
@Getter@Setter
public class User {
    private String name;
    private final String mail;
    private final String password;
    private UserRules role;

    public User(String name,String mail, String password, UserRules role) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }
}
