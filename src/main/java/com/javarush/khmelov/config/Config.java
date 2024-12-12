package com.javarush.khmelov.config;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;

public class Config {

    private final UserService userService;

    public Config(UserService userService) {
        this.userService = userService;
    }

    public void fillEmptyRepository() {
        if (userService.get(1L).isEmpty()) {
            User admin = biuldUser("Carl", "admin", Role.ADMIN);
            userService.create(admin);
            User alisa = biuldUser("Alisa", "qwerty", Role.USER);
            userService.create(alisa);
            User bob = biuldUser("Bob", "123", Role.GUEST);
            userService.create(bob);
        }
    }

    private static User biuldUser(String name, String password, Role role) {
        return User.builder()
                .login(name)
                .password(password)
                .role(role)
                .build();
    }
}
