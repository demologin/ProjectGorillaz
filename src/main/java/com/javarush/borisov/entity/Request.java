package com.javarush.borisov.entity;

import java.util.Date;
import java.util.Map;

public class Request {
    long id;
    Date createDate;
    Date closeDate;
    String requestNumber;
    String address;
    Map<String,String> parameters;

}
