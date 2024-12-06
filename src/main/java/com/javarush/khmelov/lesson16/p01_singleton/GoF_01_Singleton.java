package com.javarush.khmelov.lesson16.p01_singleton;

public class GoF_01_Singleton {
    private static GoF_01_Singleton instance;

    //конструктор
    private GoF_01_Singleton() {
    }

    //получим объект (если нужно создадим его)
    public static GoF_01_Singleton getInstance() {
        if (instance == null) {
            instance = new GoF_01_Singleton();
        }
        return instance;
    }

    public static void main(String[ ] args) {
        System.out.println(GoF_01_Singleton.getInstance());
        System.out.println(GoF_01_Singleton.getInstance());
        System.out.println(GoF_01_Singleton.getInstance());
    }

}
