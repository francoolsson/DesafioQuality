package com.example.demo.utils;

import com.example.demo.DTO.HotelDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestUtilsStatic<T> {

    public static List<HotelDTO> createListTest(String location){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<HotelDTO> list = mapper.readValue(new File(location), new TypeReference<List<HotelDTO>>() {
            });
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
