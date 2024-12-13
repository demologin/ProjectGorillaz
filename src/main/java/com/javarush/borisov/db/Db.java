package com.javarush.borisov.db;

import com.javarush.borisov.entity.Contragent;
import com.javarush.borisov.entity.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db {
    Map<Contragent, List<Request>> contragentRequests = new HashMap<>();
}
