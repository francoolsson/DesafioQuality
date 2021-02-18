package com.example.demo.service.Impl;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.repository.HotelRepo;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    public HotelServiceImpl (@Autowired HotelRepo hotelRepo) {this.hotelRepo=hotelRepo;}
    private final HotelRepo hotelRepo;

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepo.getAllHotels();
    }
}
