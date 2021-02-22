package com.example.demo.component.Impl;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.component.HotelsFiltersFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

@Component
public class HotelsFilterFactoryImpl implements HotelsFiltersFactory {

    @Override
    public Predicate<HotelDTO> getHotelsFilters(SearchHotelDatesDTO searchHotelDatesDTO) {
        return generateFilters( searchHotelDatesDTO ).stream().reduce( a->true, Predicate::and );
    }

    private List<Predicate<HotelDTO>> generateFilters (SearchHotelDatesDTO searchHotelDatesDTO){

        List<Predicate<HotelDTO>> filters = new ArrayList<>();

        if (searchHotelDatesDTO.getDestination() != null)
            filters.add(p->p.getLocation().toLowerCase( ).contains( searchHotelDatesDTO.getDestination().toLowerCase() ));

        if (searchHotelDatesDTO.getDateFrom() != null)
            filters.add(p->p.getAvailableFrom().isBefore( searchHotelDatesDTO.getDateFrom().plusDays( 1 )));

        if (searchHotelDatesDTO.getDateTo() != null)
            filters.add(p->p.getAvailableUntil().plusDays( 1 ).isAfter( searchHotelDatesDTO.getDateTo() ));

        if (searchHotelDatesDTO.getHotelCode() != null)
            filters.add(p->p.getHotelCode().toLowerCase( ).contains( searchHotelDatesDTO.getHotelCode().toLowerCase() ));

        return filters;
    }


}
