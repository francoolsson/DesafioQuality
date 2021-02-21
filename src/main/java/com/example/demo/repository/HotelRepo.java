package com.example.demo.repository;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.ReservedDatesDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;

import java.util.List;

public interface HotelRepo {

    List<HotelDTO> getHotels(SearchHotelDatesDTO searchHotelDatesDTO);
    void reserveDate(ReservedDatesDTO reservedDatesDTO);
}
