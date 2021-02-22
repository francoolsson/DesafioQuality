package com.example.demo.repository.Impl;

import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.SearchFlightDatesDTO;
import com.example.demo.component.FlightsFilterFactory;
import com.example.demo.component.HotelsFiltersFactory;
import com.example.demo.component.Impl.FlightsFilterFactoryImpl;
import com.example.demo.database.Database;
import com.example.demo.repository.FlightRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepoImpl implements FlightRepo {

    public FlightRepoImpl(@Autowired Database database){
        this.database=database;
        this.flightsFilterFactory=new FlightsFilterFactoryImpl();
        this.objectMapper=new ObjectMapper();
    }

    private final Database database;
    private final FlightsFilterFactory flightsFilterFactory;
    private final ObjectMapper objectMapper;

    @Override
    public List<FlightDTO> getFlights(SearchFlightDatesDTO searchFlightDatesDTO) {
        return database.getFlightsDatabase().stream().filter( flightsFilterFactory.getFlightsFilters( searchFlightDatesDTO ) ).collect( Collectors.toList());
    }
}
