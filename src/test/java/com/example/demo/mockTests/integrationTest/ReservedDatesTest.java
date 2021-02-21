package com.example.demo.mockTests.integrationTest;


import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.ReservedDatesDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.component.Impl.HotelsFilterFactoryImpl;
import com.example.demo.database.Database;
import com.example.demo.database.Impl.DatabasesImpl;
import com.example.demo.repository.Impl.HotelRepoImpl;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ReservedDatesTest {

    Database database;
    HotelRepoImpl hotelRepoImpl;
    HotelsFilterFactoryImpl hotelsFilterFactory;
    SearchHotelDatesDTO searchHotelDatesDTO;
    List<HotelDTO> hotels;
    DateValidator dateValidator;
    ReservedDatesDTO reservedDatesDTO;



    @BeforeEach
    void setUp() {
        this.hotelsFilterFactory = new HotelsFilterFactoryImpl();
        this.database = new DatabasesImpl();
        this.hotelRepoImpl = new HotelRepoImpl( database, hotelsFilterFactory );
        this.dateValidator = new DateValidatorImpl();
        this.searchHotelDatesDTO = new SearchHotelDatesDTO();
        this.reservedDatesDTO = new ReservedDatesDTO();
    }

    @Test
    void setPartialReservedDates () {
        reservedDatesDTO.setHotelCode( "BG-0004" );
        reservedDatesDTO.setDateFrom( dateValidator.strToLocalDate( "10/05/2021" ) );
        reservedDatesDTO.setDateTo( dateValidator.strToLocalDate( "19/05/2021" ) );
        hotelRepoImpl.reserveDate( reservedDatesDTO );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        for (HotelDTO hotelDTO : hotels) System.out.println(hotelDTO+"\n");

        Assertions.assertEquals( 13, hotels.size() );

    }

    @Test
    void setAllReservedDates(){
        reservedDatesDTO.setHotelCode( "CP-0002" );
        reservedDatesDTO.setDateFrom( dateValidator.strToLocalDate( "10/02/2021" ) );
        reservedDatesDTO.setDateTo( dateValidator.strToLocalDate( "20/03/2021" ));
        hotelRepoImpl.reserveDate( reservedDatesDTO );
        hotels=hotelRepoImpl.getHotels( searchHotelDatesDTO );

        Assertions.assertEquals( 11,hotels.size() );
    }



}

