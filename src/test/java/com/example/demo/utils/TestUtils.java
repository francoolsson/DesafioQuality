package com.example.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestUtils<T> {

    public static <T> List<T> createListTest(String location, Class<T> elementClass){
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType( ArrayList.class, elementClass );
            List<T> list = mapper.readValue(new File(location), listType);
            return list;
        } catch (IOException e) {
            return null;
        }
    }

    public static String convertObjectAsString(Object objectToTransform) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(objectToTransform);
        return result;
    }

    public static String readFileAsString(String path) {
        String text = "";
        try {
            text = new String( Files.readAllBytes( Paths.get(path))); }
        catch (IOException e) { e.printStackTrace(); }
        return text;
    }


}
