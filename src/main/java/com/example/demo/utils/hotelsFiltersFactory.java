package com.example.demo.utils;

import com.example.demo.DTO.HotelDTO;

import java.util.function.Predicate;

public interface hotelsFiltersFactory {

    Predicate<HotelDTO> getHotelsFilters();

}
