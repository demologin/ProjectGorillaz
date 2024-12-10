package com.javarush.khmelov.controller;

import com.javarush.khmelov.cmd.Command;
import com.javarush.khmelov.config.Winter;
import jakarta.servlet.http.HttpServletRequest;

public class HttpResolver {

    public Command resolve(HttpServletRequest request) {
        try {
            String requestURI = request.getRequestURI();
            requestURI = requestURI.equals("/") ? "/home" : requestURI;
            String kebabName = requestURI.split("[?#/]")[1];
            String simpleName = convertKebabStyleToCamelCase(kebabName);
            String fullName = Command.class.getPackageName() + "." + simpleName;
            Class<?> aClass = Class.forName(fullName);
            return (Command) Winter.find(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertKebabStyleToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (c == '-') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }
        return result.toString();
    }
}
