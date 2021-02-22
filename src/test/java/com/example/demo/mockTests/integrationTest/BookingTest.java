package com.example.demo.mockTests.integrationTest;


import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.ReservedDatesDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.request.UserBookingDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.component.Impl.HotelsFilterFactoryImpl;
import com.example.demo.database.Database;
import com.example.demo.database.Impl.DatabasesImpl;
import com.example.demo.repository.Impl.HotelRepoImpl;
import com.example.demo.service.HotelService;
import com.example.demo.service.Impl.HotelServiceImpl;
import com.example.demo.utils.TestUtils;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookingTest {

    Database database;
    HotelRepoImpl hotelRepoImpl;
    HotelsFilterFactoryImpl hotelsFilterFactory;
    SearchHotelDatesDTO searchHotelDatesDTO;
    List<HotelDTO> hotels;
    DateValidator dateValidator;
    ReservedDatesDTO reservedDatesDTO;
    HotelService hotelService;
    UserBookingDTO userBookingDTO;
    SearchHotelDTO searchHotelDTO;


    @BeforeEach
    void setUp() {
        this.hotelsFilterFactory = new HotelsFilterFactoryImpl();
        this.database = new DatabasesImpl();
        this.hotelRepoImpl = new HotelRepoImpl( database, hotelsFilterFactory );
        this.dateValidator = new DateValidatorImpl();
        this.searchHotelDatesDTO = new SearchHotelDatesDTO();
        this.searchHotelDTO= new SearchHotelDTO();
        this.reservedDatesDTO = new ReservedDatesDTO();
        this.hotelService=new HotelServiceImpl( hotelRepoImpl );
        this.userBookingDTO= TestUtils.createObjectTest( "src/test/java/com/example/demo/component/bookingList.json", UserBookingDTO.class );
    }

    @Test
    void setBookingTest () {

        ResponseBookingDTO responseBookingDTO = hotelService.getBooking( userBookingDTO );
        Assertions.assertEquals( 13, hotelService.getHotels(searchHotelDTO).getHotels().size() );

    }

}

