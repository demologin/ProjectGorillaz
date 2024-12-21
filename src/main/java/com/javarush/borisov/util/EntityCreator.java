package com.javarush.borisov.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.javarush.borisov.config.ClassCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class EntityCreator {
    ObjectMapper mapper = new JsonMapper(new JsonFactory());
    JsonReader jsonReader = ClassCreator.get(JsonReader.class);

    public <T> List<T> create(String file,Class<T> clazz) {
        List<Map<String, Object>> read = jsonReader.read(file);
        List<T> entities = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : read) {
            T entity = mapper.convertValue(stringObjectMap,clazz);
            entities.add(entity);
        }
        return entities;
    }

}
