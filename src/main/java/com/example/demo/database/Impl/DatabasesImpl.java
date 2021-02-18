package com.example.demo.database.Impl;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.Exceptions.ServerException;
import com.example.demo.database.Database;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;

import java.util.List;

@Repository
public class DatabasesImpl implements Database {

    private final ObjectMapper objectMapper;
    private final List<HotelDTO> hotelsDatabase;


    private List<HotelDTO> loadHotelsDatabase() throws ServerException {
        List<HotelDTO> hotels;
        try{
            hotels=objectMapper.readValue(new File( "src/main/java/com/example/demo/database/hotels.json" ),
                    new TypeReference<>(){});
        } catch (Exception e){throw new ServerException("Hotels Database is unavailable");}
        return hotels;
    }


    public DatabasesImpl(){
        this.objectMapper= new ObjectMapper();
        this.hotelsDatabase=loadHotelsDatabase();
    }


    @Override
    public List<HotelDTO> getHotelsDatabase() {
        return hotelsDatabase;
    }
}
