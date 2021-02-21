package com.example.demo.controller;

import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {

    public RestController (@Autowired HotelService hotelService){this.hotelService=hotelService;}
    private final HotelService hotelService;


    @GetMapping("/hotels")
    public ResponseHotelDTO getHotels (SearchHotelDTO searchHotelDTO){
        return hotelService.getHotels(searchHotelDTO);
    }



}
