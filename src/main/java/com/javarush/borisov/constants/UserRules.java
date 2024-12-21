package com.javarush.borisov.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public enum UserRules {
    ADMIN(List.of("", "/start-page", "/requests-list", "/login")),
    COORDINATOR(List.of("", "/start-page", "/requests-list", "/login")),
    ENGINEER(List.of("", "/start-page", "/requests-list", "/login")),
    GUEST(List.of("", "/start-page", "/login"));

    private final List<String>  permissions;

    UserRules(List<String> permissions) {
        this.permissions = permissions;
    }
}
