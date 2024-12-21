package com.javarush.shakhurov.utils;

public class RandomInt {

    public static int getRandomInt(int min, int max) {
        return (int) (min + Math.random() * max + 1);
    }
}
