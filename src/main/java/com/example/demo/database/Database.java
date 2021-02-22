package com.example.demo.database;

import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;

import java.util.List;

public interface Database {

    //Para Hoteles
    List<HotelDTO> getHotelsDatabase ();
    void deleteHotel(String code);
    void addHotel(HotelDTO hotelDTO);
    HotelDTO getHotel(String code);
    void saveBooking (ResponseBookingDTO responseBookingDTO);
    List<ResponseBookingDTO> getAllBooking();

    //Para Aviones
    List<FlightDTO> getFlightsDatabase();

}
