package com.example.demo.MockTests.serviceTests;

import com.example.demo.DTO.SearchHotelDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.exceptions.DateException;
import com.example.demo.repository.HotelRepo;
import com.example.demo.repository.Impl.HotelRepoImpl;
import com.example.demo.service.HotelService;
import com.example.demo.service.Impl.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetHotelTest {


    @Mock
    HotelRepo hotelRepo;

    HotelService hotelService;
    DateValidator dateValidator;

    @BeforeEach
    void setUp(){
        this.dateValidator=new DateValidatorImpl();
        this.hotelService= new HotelServiceImpl( hotelRepo,dateValidator );
    }

    @Test
    void exceptionTest(){
        SearchHotelDTO searchHotelDTO = new SearchHotelDTO();
        searchHotelDTO.setDateFrom( "jkbk" );

        DateException dateException= Assertions.assertThrows( DateException.class, () ->hotelService.getHotels( searchHotelDTO ) );
        Assertions.assertEquals( "Invalid Date From",dateException.getMessage() );
    }


}
