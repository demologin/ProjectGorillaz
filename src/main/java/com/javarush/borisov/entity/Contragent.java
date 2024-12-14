package com.javarush.borisov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Setter

public class Contragent {

    public String name;
    public List<Request> contragentRequests = new ArrayList<>();

    @Override
    public String toString() {
        return "Contragent{" +
                "name='" + name + '\'' +
                '}';
    }
}
