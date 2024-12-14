package com.javarush.borisov.db;

import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.entity.Contragent;
import com.javarush.borisov.entity.Request;
import com.javarush.borisov.util.EntityCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Db {
    public List<Contragent> contragents = new ArrayList<>(); ;
    public List<Request> requests = new ArrayList<>();


}
