package com.example.demo.component;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDatesDTO;

import java.util.function.Predicate;

public interface HotelsFiltersFactory {

    Predicate<HotelDTO> getHotelsFilters(SearchHotelDatesDTO searchHotelDatesDTO);

}
