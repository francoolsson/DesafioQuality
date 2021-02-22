package com.example.demo.mockTests.serviceTests;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.exceptions.DateException;
import com.example.demo.exceptions.SearchHotelException;
import com.example.demo.repository.HotelRepo;
import com.example.demo.service.HotelService;
import com.example.demo.service.Impl.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class GetHotelExceptionsTest {


    @Mock
    HotelRepo hotelRepo;

    HotelService hotelService;
    SearchHotelDTO searchHotelDTO;
    SearchHotelDatesDTO searchHotelDatesDTO;

    @BeforeEach
    void setUp(){
        this.hotelService= new HotelServiceImpl( hotelRepo);
        this.searchHotelDTO = new SearchHotelDTO();
        this.searchHotelDatesDTO = new SearchHotelDatesDTO();
    }

    @Test
    void exceptionDateFromTest(){
        searchHotelDTO.setDateFrom( "2021/10/10" );
        DateException dateException= Assertions.assertThrows( DateException.class, () ->hotelService.getHotels( searchHotelDTO ) );
        Assertions.assertEquals( "Invalid Date From",dateException.getMessage() );
    }

    @Test
    void exceptionDateToTest(){
        searchHotelDTO.setDateTo( "2021/10/10" );
        DateException dateException= Assertions.assertThrows( DateException.class, () ->hotelService.getHotels( searchHotelDTO ) );
        Assertions.assertEquals( "Invalid Date To",dateException.getMessage() );
    }

    @Test
    void equalsDatesTest(){
        searchHotelDTO.setDateFrom( "10/10/2021" );
        searchHotelDTO.setDateTo( "10/10/2021" );
        DateException dateException= Assertions.assertThrows( DateException.class, () ->hotelService.getHotels( searchHotelDTO ) );
        Assertions.assertEquals( "Date From is after or is equal Date To",dateException.getMessage() );
    }


    @Test
    void nullSearchResponse(){
        Mockito.when( hotelRepo.getHotels( searchHotelDatesDTO )).thenReturn( new ArrayList<HotelDTO>( ));
        SearchHotelException searchHotelException = Assertions.assertThrows(SearchHotelException.class, () ->hotelService.getHotels( searchHotelDTO ) );
        Assertions.assertEquals( "There are no matches with the search",searchHotelException.getMessage() );
    }


}
