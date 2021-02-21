package com.example.demo.service;

import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;

public interface HotelService {

        ResponseHotelDTO getHotels (SearchHotelDTO searchHotelDTO);

}
