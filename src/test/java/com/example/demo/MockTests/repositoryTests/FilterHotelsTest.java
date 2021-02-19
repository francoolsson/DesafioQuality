package com.example.demo.MockTests.repositoryTests;


import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDatesDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.component.Impl.HotelsFilterFactoryImpl;
import com.example.demo.database.Database;
import com.example.demo.repository.Impl.HotelRepoImpl;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class FilterHotelsTest {

    @Mock
    private Database database;

    HotelRepoImpl hotelRepoImpl;
    HotelsFilterFactoryImpl hotelsFilterFactory;
    SearchHotelDatesDTO searchHotelDatesDTO;
    List<HotelDTO> hotels;
    DateValidator dateValidator;



    @BeforeEach
    void setUp() {
        this.hotelsFilterFactory = new HotelsFilterFactoryImpl();
        this.hotelRepoImpl = new HotelRepoImpl( database, hotelsFilterFactory );
        Mockito.when( database.getHotelsDatabase() ).thenReturn(
                TestUtils.createListTest( "src/test/java/com/example/demo/component/HotelsList.json", HotelDTO.class )
        );
        this.dateValidator = new DateValidatorImpl();
        searchHotelDatesDTO = new SearchHotelDatesDTO();
        searchHotelDatesDTO.setDateFrom( null );
        searchHotelDatesDTO.setDateTo( null );
        searchHotelDatesDTO.setDestination( null );
    }

    //Testeo que devuelva todos los hoteles, los cuales son 4 en total.

    @Test
    void filterDestinationHotels() {
        searchHotelDatesDTO.setDestination( "Bogotá" );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        for (HotelDTO hotelDTO : hotels) {
            Assertions.assertEquals( hotelDTO.getLocation(), "Bogotá" );
        }
    }

    @Test
    void getAllDestinationHotelsWhenNoFilter() {
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        Assertions.assertEquals( 4, hotels.size() );

    }


    @Test
    void filterBadDestinationHotels() {
        searchHotelDatesDTO.setDestination( "vogota" );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        Assertions.assertEquals( 0, hotels.size() );
    }

    @Test
    void filterDateFromHotels() {

        LocalDate localDate =dateValidator.strToLocalDate( "24/01/2021" );
        searchHotelDatesDTO.setDateFrom( localDate );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        Assertions.assertEquals( 1, hotels.size() );
        Assertions.assertEquals( "SE-0002",hotels.get( 0 ).getHotelCode() );
    }

    @Test
    void filterDateToHotels() {

        LocalDate localDate =dateValidator.strToLocalDate( "14/10/2021" );
        searchHotelDatesDTO.setDateTo( localDate );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        Assertions.assertEquals( 1, hotels.size() );
        Assertions.assertEquals( "SE-0002",hotels.get( 0 ).getHotelCode() );
    }

    @Test
    void filterNoneDateToHotels() {

        LocalDate localDate =dateValidator.strToLocalDate( "15/10/2021" );
        searchHotelDatesDTO.setDateTo( localDate );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        Assertions.assertEquals( 0, hotels.size() );
    }

    @Test
    void AllfilterHotels() {

        //searchHotelDatesDTO.setDestination( "Bogotá" );
        LocalDate localDate1 =dateValidator.strToLocalDate( "14/10/2021" );
        searchHotelDatesDTO.setDateFrom( localDate1 );
        LocalDate localDate2 =dateValidator.strToLocalDate( "14/10/2021" );
        searchHotelDatesDTO.setDateTo( localDate2 );
        hotels = hotelRepoImpl.getHotels( searchHotelDatesDTO );
        Assertions.assertEquals( 1, hotels.size() );
        Assertions.assertEquals( "SE-0002",hotels.get( 0 ).getHotelCode() );
    }


}

