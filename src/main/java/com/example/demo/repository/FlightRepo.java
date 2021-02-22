package com.example.demo.repository;

import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.SearchFlightDatesDTO;

import java.util.List;

public interface FlightRepo {
    List<FlightDTO> getFlights(SearchFlightDatesDTO searchFlightDatesDTO);
}
