package com.javarush.borisov.cmd;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    default String doGet(HttpServletRequest req){
        return getView();
    }
    default String doPost(HttpServletRequest req){
        return getView();
    }

    default String getView() {
        String simpleName = this.getClass().getSimpleName();
        return convertToKebab(simpleName);
    }

    private static String convertToKebab(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i)) && i==0) {
                result.append(Character.toLowerCase(input.charAt(i)));
            }else if(Character.isUpperCase(input.charAt(i))){
                result.append("-");
                result.append(Character.toLowerCase(input.charAt(i)));
            }else result.append(input.charAt(i));
        }
        return result.toString();
    }

}
