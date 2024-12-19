package com.javarush.borisov.entity;

import java.nio.file.Path;
import java.util.Date;
import java.util.Map;

public class Request {

    Date createDate;
    Date closeDate;
    String requestNumber;
    String address;
    Path link;
    Map<String,String> parameters;
    boolean isClosed;

}
