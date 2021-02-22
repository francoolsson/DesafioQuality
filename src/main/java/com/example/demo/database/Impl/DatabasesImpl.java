package com.example.demo.database.Impl;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;
import com.example.demo.exceptions.ServerException;
import com.example.demo.database.Database;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DatabasesImpl implements Database {

    private final ObjectMapper objectMapper;
    private final Map<String,HotelDTO> hotelsDatabase;
    private final List<ResponseBookingDTO> bookingDatabase;


    private Map <String,HotelDTO> loadHotelsDatabase() throws ServerException {
        Map <String,HotelDTO> hotelsDatabase = new HashMap<>();
        List<HotelDTO> hotels;
        try{
            hotels=objectMapper.readValue(new File( "src/main/java/com/example/demo/database/hotels.json" ),
                    new TypeReference<>(){});
        } catch (Exception e){throw new ServerException("Hotels Database is unavailable");}
        for (HotelDTO hotelDTO : hotels){
            hotelsDatabase.put( hotelDTO.getHotelCode(),hotelDTO );
        }
        return hotelsDatabase;
    }


    public DatabasesImpl(){
        this.objectMapper= new ObjectMapper();
        this.hotelsDatabase=loadHotelsDatabase();
        this.bookingDatabase=new ArrayList<>();
    }


    @Override
    public List<HotelDTO> getHotelsDatabase() {
        return hotelsDatabase.values().stream().collect( Collectors.toList());
    }

    @Override
    public void deleteHotel(String code) {
        hotelsDatabase.remove( code );
    }

    @Override
    public void addHotel(HotelDTO hotelDTO) {
        hotelsDatabase.put( hotelDTO.getHotelCode(),hotelDTO );
    }

    @Override
    public HotelDTO getHotel(String code) {
        return hotelsDatabase.get( code );
    }

    @Override
    public void saveBooking(ResponseBookingDTO responseBookingDTO) {
        bookingDatabase.add( responseBookingDTO );
    }

    @Override
    public List<ResponseBookingDTO> getAllBooking() {
        return bookingDatabase;
    }
}
