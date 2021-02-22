package com.example.demo.mockTests.integrationTest;


import com.example.demo.DTO.intern.BookingDTO;
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
import com.example.demo.exceptions.BookingException;
import com.example.demo.exceptions.SearchHotelException;
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
    void IntegrationBookingTest () {
        ResponseBookingDTO responseBookingDTO = hotelService.getBooking( userBookingDTO );
        Assertions.assertEquals( 13, hotelService.getHotels(searchHotelDTO).getHotels().size() );
        Assertions.assertEquals( 200,responseBookingDTO.getStatusCode().getCode() );
        Assertions.assertEquals( 19939.5,responseBookingDTO.getTotal() );
        BookingDTO bookingDTO=responseBookingDTO.getBooking();
        bookingDTO.setDateFrom( "10/02/2021" );
        bookingDTO.setDateTo( "11/02/2021" );
        responseBookingDTO = hotelService.getBooking( userBookingDTO );
        Assertions.assertEquals( 12,hotelService.getHotels( searchHotelDTO ).getHotels().size() );
        SearchHotelException searchHotelException = Assertions.assertThrows( SearchHotelException.class, ()-> hotelService.getBooking( userBookingDTO ));
        Assertions.assertEquals( "There are no matches with the search",searchHotelException.getMessage() );
    }

    @Test
    void badEmailBookingTest(){
        userBookingDTO.setUserName( "mal:)" );
        BookingException bookingException= Assertions.assertThrows( BookingException.class,
                ()-> hotelService.getBooking( userBookingDTO ));
        Assertions.assertEquals( "Email is not valid",bookingException.getMessage() );

    }

    @Test
    void badPeopleQuantityTest(){
        userBookingDTO.getBooking().setPeopleAmount(4);
        BookingException bookingException= Assertions.assertThrows( BookingException.class,
                ()-> hotelService.getBooking( userBookingDTO ));
        Assertions.assertEquals( "People´s list do not equal people amount",bookingException.getMessage() );
    }

    @Test
    void badInfoPersonTest(){
        userBookingDTO.getBooking().getPeople().get( 0 ).setDni( null );
        BookingException bookingException= Assertions.assertThrows( BookingException.class,
                ()-> hotelService.getBooking( userBookingDTO ));
        Assertions.assertEquals( "All the people on the list must have the following complete fields: name, surname, DNI and date of birth.",bookingException.getMessage() );
    }

    @Test
    void badDebitHasNotDuesTest(){
        userBookingDTO.getBooking().getPaymentMethod().setType( "DEBIT" );
        BookingException bookingException= Assertions.assertThrows( BookingException.class,
                ()-> hotelService.getBooking( userBookingDTO ));
        Assertions.assertEquals( "Payment Error",bookingException.getMessage() );
    }

    @Test
    void debitHasNotDuesTest(){
        userBookingDTO.getBooking().getPaymentMethod().setType( "DEBIT" );
        userBookingDTO.getBooking().getPaymentMethod().setDues(1);
        ResponseBookingDTO responseBookingDTO=hotelService.getBooking( userBookingDTO );
        Assertions.assertEquals( 200,responseBookingDTO.getStatusCode().getCode() );
    }

    @Test
    void peopleAmountExceedRoomTest(){
        BookingDTO bookingDTO= userBookingDTO.getBooking();
        bookingDTO.setDateFrom( "11/02/2021" );
        bookingDTO.setDateTo( "16/02/2021" );
        bookingDTO.setDestination( "Buenos Aires" );
        bookingDTO.setHotelCode( "HB-0001" );
        BookingException bookingException= Assertions.assertThrows( BookingException.class,
                ()-> hotelService.getBooking( userBookingDTO ));
        Assertions.assertEquals( "People amount exceed room´s disponibility",bookingException.getMessage() );

    }

}

