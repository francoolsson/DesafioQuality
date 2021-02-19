package com.example.demo.repository;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDTO;
import com.example.demo.DTO.SearchHotelDatesDTO;

import java.util.List;

public interface HotelRepo {

    List<HotelDTO> getHotels(SearchHotelDatesDTO searchHotelDatesDTO);

}
