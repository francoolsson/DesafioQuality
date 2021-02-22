package com.example.demo.mockTests.databaseTests;

import com.example.demo.DTO.intern.FlightDTO;
import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.database.Database;
import com.example.demo.database.Impl.DatabasesImpl;
import com.example.demo.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ConstructFlightsDatabaseTest {

    Database database;

    @BeforeEach
    void setUp(){database=new DatabasesImpl(); }

    @Test
    void flightDatabaseTest() throws Exception{

        List<FlightDTO> flightsTest=database.getFlightsDatabase();
        List<FlightDTO> resultsToCompare= TestUtils.createListTest( "src/main/java/com/example/demo/database/flights.json" ,FlightDTO.class);
        Assertions.assertEquals(flightsTest.size(), resultsToCompare.size());


    }
}
