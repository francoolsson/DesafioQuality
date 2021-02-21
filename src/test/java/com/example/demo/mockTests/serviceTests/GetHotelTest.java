package com.example.demo.mockTests.serviceTests;

import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.repository.HotelRepo;
import com.example.demo.service.HotelService;
import com.example.demo.service.Impl.HotelServiceImpl;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class GetHotelTest {


    @Mock
    private HotelRepo hotelRepo;

    HotelService hotelService;
    DateValidator dateValidator;
    SearchHotelDTO searchHotelDTO;
    ResponseHotelDTO responseHotelDTO;
    SearchHotelDatesDTO searchHotelDatesDTO;

    @BeforeEach
    void setUp(){
        this.dateValidator=new DateValidatorImpl();
        this.hotelService= new HotelServiceImpl( hotelRepo,dateValidator );
        this.searchHotelDTO = new SearchHotelDTO();
        this.searchHotelDatesDTO = new SearchHotelDatesDTO();
    }


    @Test
    void respondeHotelTest (){

        Mockito.when( hotelRepo.getHotels( any(SearchHotelDatesDTO.class) ) ).thenReturn(
                TestUtils.createListTest( "src/test/java/com/example/demo/component/HotelsList.json", HotelDTO.class )
        );
        responseHotelDTO = hotelService.getHotels( searchHotelDTO );
        Assertions.assertEquals( 200, responseHotelDTO.getCode() );
        Assertions.assertEquals( "ok",responseHotelDTO.getStatus() );
        Assertions.assertEquals( 4,responseHotelDTO.getHotels().size() );
    }





}
