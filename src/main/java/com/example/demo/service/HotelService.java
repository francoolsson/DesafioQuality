package com.example.demo.service;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;

import java.util.List;

public interface HotelService {

        ResponseHotelDTO getHotels (SearchHotelDTO searchHotelDTO);

}
