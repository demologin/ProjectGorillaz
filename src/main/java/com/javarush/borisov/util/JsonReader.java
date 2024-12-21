package com.javarush.borisov.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

public class JsonReader {
    private final ObjectMapper mapper = new JsonMapper(new JsonFactory());


    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> read(String path){
        List<Map<String, Object>> readedObjects;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            readedObjects = mapper.readValue(inputStream, List.class);
        } catch (IOException e) {
            e.printStackTrace(); //TODO log
            return null;
        }
        return readedObjects;
    }
}
