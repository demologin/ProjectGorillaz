package com.javarush.borisov.cmd;

import com.javarush.borisov.config.ClassCreator;
import jakarta.servlet.http.HttpServletRequest;

public class HttpResolver {

    public Command resolve(HttpServletRequest req) {
        try {
            String requestURI = req.getRequestURI();
            requestURI = requestURI.equals("/") ? "/start-page" : requestURI;
            String clazzName = convertToCamelCase(requestURI);
            String fullName = this.getClass().getPackageName() + "." + clazzName;

            Class<?> clazz = Class.forName(fullName);
            return (Command) ClassCreator.get(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertToCamelCase(String requestURI) {
        requestURI = requestURI.replace(requestURI.substring(0, requestURI.indexOf('/') + 1),"");
        boolean upperNext = true;
        StringBuilder camelcase = new StringBuilder();
        for (int i = 0; i < requestURI.length(); i++) {
            if (requestURI.charAt(i) == '-') {
                upperNext = true;

            } else {
                if (upperNext) {
                    camelcase.append(Character.toUpperCase(requestURI.charAt(i)));
                    upperNext = false;
                } else {
                    camelcase.append(requestURI.charAt(i));
                }

            }
        }
        return camelcase.toString();
    }
}
