package com.javarush.borisov.entity;

import lombok.Setter;


@Setter
public class Contragent {

    public String name;

    @Override
    public String toString() {
        return "Contragent{" +
               "name='" + name + '}';
    }
}
