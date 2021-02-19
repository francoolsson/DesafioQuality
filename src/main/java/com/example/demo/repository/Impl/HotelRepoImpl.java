package com.example.demo.repository.Impl;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDatesDTO;
import com.example.demo.database.Database;
import com.example.demo.repository.HotelRepo;
import com.example.demo.component.HotelsFiltersFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HotelRepoImpl implements HotelRepo {

    public HotelRepoImpl (@Autowired Database database, @Autowired HotelsFiltersFactory hotelsFiltersFactory) {
        this.database=database;
        this.hotelsFiltersFactory=hotelsFiltersFactory;
    }
    private final Database database;
    private final HotelsFiltersFactory hotelsFiltersFactory;



    @Override
    public List<HotelDTO> getHotels(SearchHotelDatesDTO searchHotelDatesDTO) {
        return database.getHotelsDatabase().stream().filter( hotelsFiltersFactory.getHotelsFilters( searchHotelDatesDTO ) ).collect( Collectors.toList());
    }



}
