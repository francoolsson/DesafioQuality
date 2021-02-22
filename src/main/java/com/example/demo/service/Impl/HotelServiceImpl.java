package com.example.demo.service.Impl;

import com.example.demo.DTO.intern.BookingDTO;
import com.example.demo.DTO.intern.HotelDTO;
import com.example.demo.DTO.intern.ReservedDatesDTO;
import com.example.demo.DTO.request.SearchHotelDTO;
import com.example.demo.DTO.intern.SearchHotelDatesDTO;
import com.example.demo.DTO.request.UserBookingDTO;
import com.example.demo.DTO.response.ResponseBookingDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import com.example.demo.DTO.response.StatusDTO;
import com.example.demo.component.BookingValidator;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.BookingValidatorImpl;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.exceptions.BookingException;
import com.example.demo.exceptions.DateException;
import com.example.demo.exceptions.SearchHotelException;
import com.example.demo.repository.HotelRepo;
import com.example.demo.service.HotelService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    public HotelServiceImpl (@Autowired HotelRepo hotelRepo) {
        this.hotelRepo=hotelRepo;
        this.dateValidator= new DateValidatorImpl();
        this.bookingValidator = new BookingValidatorImpl();

    }
    private final HotelRepo hotelRepo;
    private final DateValidator dateValidator;
    private final BookingValidator bookingValidator;


    @Override
    public ResponseHotelDTO getHotels(SearchHotelDTO searchHotelDTO) {

        SearchHotelDatesDTO searchHotelDatesDTO = searchDTOValidator( searchHotelDTO );
        ResponseHotelDTO responseHotelDTO = new ResponseHotelDTO();
        responseHotelDTO.setCode( 200 );
        responseHotelDTO.setStatus( "ok" );
        responseHotelDTO.setHotels(hotelRepo.getHotels( searchHotelDatesDTO ));
        return responseHotelDTO;

        //return hotelRepo.getHotels(searchHotelDTO);
    }


    @Override
    public ResponseBookingDTO getBooking(UserBookingDTO userBookingDTO) {

        if (!(bookingValidator.mailValidator( userBookingDTO.getUserName() )) || userBookingDTO.getUserName().isEmpty())
            throw new BookingException("Email is not valid");
        if (!(bookingValidator.peopleQuantity( userBookingDTO.getBooking() )))
            throw new BookingException("People´s list do not equal people amount");
        if(!(bookingValidator.peopleValidator( userBookingDTO.getBooking().getPeople() )))
            throw new BookingException(("All the people on the list must have the following complete fields: name, surname, DNI and date of birth."));

        BookingDTO bookingDTO = userBookingDTO.getBooking();
        SearchHotelDTO searchHotelDTO = new SearchHotelDTO();
        searchHotelDTO.setDestination( bookingDTO.getDestination() );
        searchHotelDTO.setDateTo( bookingDTO.getDateTo() );
        searchHotelDTO.setDateFrom( bookingDTO.getDateFrom() );
        List<HotelDTO> hotels= getHotels( searchHotelDTO ).getHotels();
        if (hotels.isEmpty()) throw new BookingException("Filters Error");
        hotels=hotels.stream().filter( u -> u.getHotelCode().contains( bookingDTO.getHotelCode())).
                collect( Collectors.toList());
        if (hotels.size()!=1) throw new BookingException("Probably hotel´s code error");
        HotelDTO hotelDTO = hotels.get( 0 );
        ReservedDatesDTO reservedDatesDTO = new ReservedDatesDTO();
        reservedDatesDTO.setDateTo( dateValidator.strToLocalDate( bookingDTO.getDateTo() ) );
        reservedDatesDTO.setDateFrom( dateValidator.strToLocalDate( bookingDTO.getDateFrom() ) );
        reservedDatesDTO.setHotelCode( hotelDTO.getHotelCode() );
        hotelRepo.reserveDate( reservedDatesDTO );
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus( "ok" );
        statusDTO.setCode( 200 );
        ResponseBookingDTO responseBookingDTO = new ResponseBookingDTO();
        responseBookingDTO.setBooking( bookingDTO );
        responseBookingDTO.setStatusCode( statusDTO );
        responseBookingDTO.setUserName( userBookingDTO.getUserName() );
        return responseBookingDTO;
    }

    private SearchHotelDatesDTO searchDTOValidator (SearchHotelDTO searchHotelDTO){

        SearchHotelDatesDTO searchHotelDatesDTO = new SearchHotelDatesDTO();
        if (searchHotelDTO.getDateFrom() != null){
            if (dateValidator.isValid( searchHotelDTO.getDateFrom() )) {
                searchHotelDatesDTO.setDateFrom(dateValidator.strToLocalDate( searchHotelDTO.getDateFrom() ).plusDays( 1 ));
            }
            else throw new DateException("Invalid Date From");
        }
        if (searchHotelDTO.getDateTo() != null){
            if (dateValidator.isValid( searchHotelDTO.getDateTo() )) {
                searchHotelDatesDTO.setDateTo(dateValidator.strToLocalDate( searchHotelDTO.getDateTo() ));
            }
            else throw new DateException("Invalid Date To");
        }
        if (searchHotelDatesDTO.getDateFrom()!=null && searchHotelDatesDTO.getDateTo()!=null){
            if (searchHotelDatesDTO.getDateFrom().isAfter( searchHotelDatesDTO.getDateTo() ) ||
                    searchHotelDatesDTO.getDateFrom().isEqual( searchHotelDatesDTO.getDateTo() )) {
                throw new DateException("Date From is after or is equal Date To");
            }
        }
        searchHotelDatesDTO.setDestination( searchHotelDTO.getDestination() );
        if (hotelRepo.getHotels( searchHotelDatesDTO ).isEmpty()) {
            throw new SearchHotelException("There are no matches with the search");
        }
        return searchHotelDatesDTO;
    }
}
