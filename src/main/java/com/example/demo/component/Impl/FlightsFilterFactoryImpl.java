package com.example.demo.component.Impl;

import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.SearchFlightDatesDTO;
import com.example.demo.component.FlightsFilterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FlightsFilterFactoryImpl implements FlightsFilterFactory {

    @Override
    public Predicate<FlightDTO> getFlightsFilters(SearchFlightDatesDTO searchFlightDatesDTO) {
        return generateFilters( searchFlightDatesDTO ).stream().reduce( a->true, Predicate::and);
    }

    private List<Predicate<FlightDTO>> generateFilters(SearchFlightDatesDTO searchFlightDatesDTO){

        List<Predicate<FlightDTO>> filters = new ArrayList<>();

        if (searchFlightDatesDTO.getFlightNumber() != null)
            filters.add(p->p.getFlightNumber().toLowerCase( ).matches( searchFlightDatesDTO.getFlightNumber().toLowerCase() ));

        if (searchFlightDatesDTO.getDateFrom() != null)
            filters.add(p->p.getDepartureDate().plusDays( 1 ).isAfter( searchFlightDatesDTO.getDateFrom()));

        if (searchFlightDatesDTO.getDateTo() != null)
            filters.add(p->p.getReturnDate().isBefore( searchFlightDatesDTO.getDateTo().plusDays( 1 ) ));

        if (searchFlightDatesDTO.getOrigin() != null)
            filters.add(p->p.getOrigin().toLowerCase( ).contains( searchFlightDatesDTO.getOrigin().toLowerCase() ));

        if (searchFlightDatesDTO.getDestination() != null)
            filters.add(p->p.getDestination().toLowerCase( ).contains( searchFlightDatesDTO.getDestination().toLowerCase() ));

        return filters;


    }

}
