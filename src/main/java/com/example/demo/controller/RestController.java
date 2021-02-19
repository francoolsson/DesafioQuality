package com.example.demo.controller;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDTO;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {

    public RestController (@Autowired HotelService hotelService){this.hotelService=hotelService;}
    private final HotelService hotelService;


    @GetMapping("/hotels")
    public List<HotelDTO> getHotels (SearchHotelDTO searchHotelDTO){
        return hotelService.getHotels(searchHotelDTO);

    }



}
