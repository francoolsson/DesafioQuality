package com.example.demo.service;

import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.request.UserBookingDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import org.apache.catalina.User;

public interface HotelService {

        ResponseHotelDTO getHotels (SearchHotelDTO searchHotelDTO);
        ResponseBookingDTO getBooking (UserBookingDTO userBookingDTO);

}
