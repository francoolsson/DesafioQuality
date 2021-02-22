package com.example.demo.mockTests.repositoryTests;


import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.SearchFlightDatesDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.component.Impl.HotelsFilterFactoryImpl;
import com.example.demo.database.Database;
import com.example.demo.repository.Impl.FlightRepoImpl;
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
public class FilterFlightTest {

    @Mock
    private Database database;

    FlightRepoImpl flightRepo;
    SearchFlightDatesDTO searchFlightDatesDTO;
    List<FlightDTO> flight;
    DateValidator dateValidator;



    @BeforeEach
    void setUp() {
        this.flightRepo = new FlightRepoImpl( database );
        Mockito.when( database.getFlightsDatabase()).thenReturn(
                TestUtils.createListTest( "src/test/java/com/example/demo/component/flightList.json", FlightDTO.class )
        );
        this.dateValidator = new DateValidatorImpl();
        searchFlightDatesDTO=new SearchFlightDatesDTO();
    }



   @Test
   void getAllFlightWhenNoFilter() {
       flight = flightRepo.getFlights( searchFlightDatesDTO );
       Assertions.assertEquals( 4, flight.size() );

   }


    @Test
    void getFlightWhenFlightNumber() {
        searchFlightDatesDTO.setFlightNumber( "PIBA-1420-1" );
        flight = flightRepo.getFlights( searchFlightDatesDTO );
        Assertions.assertEquals( 1, flight.size() );
        Assertions.assertEquals( "PIBA-1420-1" ,flight.get( 0 ).getFlightNumber() );
    }

    @Test
    void getFlightBetweenDates(){
        searchFlightDatesDTO.setDateFrom( dateValidator.strToLocalDate( "10/02/2021" ) );
        searchFlightDatesDTO.setDateTo( dateValidator.strToLocalDate( "17/02/2021" ) );
        flight=flightRepo.getFlights( searchFlightDatesDTO );
        Assertions.assertEquals( 2,flight.size());
        Assertions.assertEquals( true, (
                (flight.get( 0 ).getFlightNumber().equals( "BATU-5536" )
                || flight.get( 0 ).getFlightNumber().equals( "BAPI-1235" ))
                && (flight.get( 1 ).getFlightNumber().equals( "BATU-5536" )
                        || flight.get( 1 ).getFlightNumber().equals( "BAPI-1235" ))
        ));

    }

    @Test
    void getFlightBetweenLocation(){
        searchFlightDatesDTO.setOrigin("Buenos Aires");
        searchFlightDatesDTO.setDestination( "Tucumán" );
        flight=flightRepo.getFlights( searchFlightDatesDTO );
        Assertions.assertEquals( 1,flight.size());
        Assertions.assertEquals( "BATU-5536" ,flight.get( 0 ).getFlightNumber());

    }




}

