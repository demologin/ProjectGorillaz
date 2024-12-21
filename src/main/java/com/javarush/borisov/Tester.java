package com.javarush.borisov;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.Db;
import com.javarush.borisov.db.DbConfig.MultiKey;
import com.javarush.borisov.util.FolderScanner;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Tester {
    @SneakyThrows
    public static void main(String[] args) {
       Db db = ClassCreator.get(Db.class);

        System.out.println(db.allDatesWeGot());
    }
}

