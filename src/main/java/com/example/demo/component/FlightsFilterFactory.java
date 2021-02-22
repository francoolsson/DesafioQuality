package com.example.demo.component;

import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.SearchFlightDatesDTO;
import com.example.demo.repository.FlightRepo;

import java.util.function.Predicate;

public interface FlightsFilterFactory {

    Predicate<FlightDTO> getFlightsFilters(SearchFlightDatesDTO searchFlightDatesDTO);
}
