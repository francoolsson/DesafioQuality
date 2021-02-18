package com.example.demo.repository.Impl;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.database.Database;
import com.example.demo.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepoImpl implements HotelRepo {

    public HotelRepoImpl (@Autowired Database database) {this.database=database;}
    private final Database database;



    @Override
    public List<HotelDTO> getAllHotels() {
        return database.getHotelsDatabase();
    }
}
