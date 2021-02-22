package com.example.demo.controller;

import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.request.UserBookingDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {

    public RestController (@Autowired HotelService hotelService){this.hotelService=hotelService;}
    private final HotelService hotelService;


    @GetMapping("/hotels")
    public ResponseHotelDTO getHotels (SearchHotelDTO searchHotelDTO){
        return hotelService.getHotels(searchHotelDTO);
    }

    @PostMapping("/booking")
    public ResponseBookingDTO getBooking(@RequestBody UserBookingDTO userBookingDTO){
        return hotelService.getBooking( userBookingDTO );
    }

    @GetMapping("/allbooking")
    public List<ResponseBookingDTO> getAllBooking (){
        return hotelService.getAllBooking();
    }


}
