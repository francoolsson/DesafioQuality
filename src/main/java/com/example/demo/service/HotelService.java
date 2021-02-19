package com.example.demo.service;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDTO;

import java.util.List;

public interface HotelService {

    List<HotelDTO> getHotels (SearchHotelDTO searchHotelDTO);

}
