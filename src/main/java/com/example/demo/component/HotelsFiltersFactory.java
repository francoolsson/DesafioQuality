package com.example.demo.component;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;

import java.util.function.Predicate;

public interface HotelsFiltersFactory {

    Predicate<HotelDTO> getHotelsFilters(SearchHotelDatesDTO searchHotelDatesDTO);

}
