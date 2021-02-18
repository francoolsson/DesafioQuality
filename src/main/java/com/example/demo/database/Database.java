package com.example.demo.database;

import com.example.demo.DTO.HotelDTO;

import java.util.List;

public interface Database {

    List<HotelDTO> getHotelsDatabase ();

}
