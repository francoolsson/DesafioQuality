package com.example.demo.repository.Impl;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.ReservedDatesDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;
import com.example.demo.database.Database;
import com.example.demo.repository.HotelRepo;
import com.example.demo.component.HotelsFiltersFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HotelRepoImpl implements HotelRepo {

    public HotelRepoImpl (@Autowired Database database, @Autowired HotelsFiltersFactory hotelsFiltersFactory) {
        this.database=database;
        this.hotelsFiltersFactory=hotelsFiltersFactory;
        this.objectMapper=new ObjectMapper();
    }
    private final Database database;
    private final HotelsFiltersFactory hotelsFiltersFactory;
    private final ObjectMapper objectMapper;



    @Override
    public List<HotelDTO> getHotels(SearchHotelDatesDTO searchHotelDatesDTO) {
        return database.getHotelsDatabase().stream().filter( hotelsFiltersFactory.getHotelsFilters( searchHotelDatesDTO ) ).collect( Collectors.toList());
    }

    @Override
    public void reserveDate(ReservedDatesDTO reservedDatesDTO) {

        HotelDTO hotelDTO= database.getHotel(reservedDatesDTO.getHotelCode());
        database.deleteHotel( reservedDatesDTO.getHotelCode() );
        if (!((reservedDatesDTO.getDateFrom().isEqual(hotelDTO.getAvailableFrom())) &&
                (reservedDatesDTO.getDateTo().isEqual( hotelDTO.getAvailableUntil() )))){
            HotelDTO hotelDTO1 = objectMapper.convertValue( hotelDTO, HotelDTO.class );
            hotelDTO.setHotelCode( hotelDTO.getHotelCode()+"-0" );
            hotelDTO.setAvailableUntil( reservedDatesDTO.getDateFrom() );
            hotelDTO1.setHotelCode( hotelDTO1.getHotelCode()+"-1" );
            hotelDTO1.setAvailableFrom( reservedDatesDTO.getDateTo() );
            if (!(hotelDTO.getAvailableFrom().equals( hotelDTO.getAvailableUntil() ))) database.addHotel( hotelDTO );
            if (!(hotelDTO1.getAvailableFrom().equals( hotelDTO1.getAvailableUntil() ))) database.addHotel( hotelDTO1 );
        }
    }


    @Override
    public void saveBooking(ResponseBookingDTO responseBookingDTO) {
        database.saveBooking( responseBookingDTO );
    }

    @Override
    public List<ResponseBookingDTO> getAllBooking() {
        return database.getAllBooking();
    }
}
