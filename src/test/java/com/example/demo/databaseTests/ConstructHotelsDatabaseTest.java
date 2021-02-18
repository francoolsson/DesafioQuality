package com.example.demo.databaseTests;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.database.Database;
import com.example.demo.database.Impl.DatabasesImpl;
import com.example.demo.utils.TestUtils;
import com.example.demo.utils.TestUtilsStatic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ConstructHotelsDatabaseTest {

    Database database;

    @BeforeEach
    void setUp(){database=new DatabasesImpl(); }

    @Test
    void hotelsDatabaseTest() throws Exception{

        List<HotelDTO> hotelsTest=database.getHotelsDatabase();
        List<HotelDTO> resultsToCompare= TestUtils.createListTest( "src/main/java/com/example/demo/database/hotels.json" , HotelDTO.class);
        for (int i=0;i<hotelsTest.size();i++)
        {
            Assertions.assertEquals(true, hotelsTest.get( i ).equals(resultsToCompare.get( i )));
        }

    }
}
