package com.javarush.shakhurov.dto;

import com.javarush.shakhurov.model.User;

import java.util.List;

public class UsersPage extends BasePage {
    private List<User> users;

    public UsersPage(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
