package com.example.demo.service.Impl;

import com.example.demo.DTO.SearchHotelDTO;
import com.example.demo.DTO.SearchHotelDatesDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.exceptions.DateException;
import com.example.demo.exceptions.SearchHotelException;
import com.example.demo.repository.HotelRepo;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    public HotelServiceImpl (@Autowired HotelRepo hotelRepo, @Autowired DateValidator dateValidator) {
        this.hotelRepo=hotelRepo;
        this.dateValidator= dateValidator;
    }
    private final HotelRepo hotelRepo;
    private final DateValidator dateValidator;


    @Override
    public ResponseHotelDTO getHotels(SearchHotelDTO searchHotelDTO) {

        SearchHotelDatesDTO searchHotelDatesDTO = new SearchHotelDatesDTO();
        if (searchHotelDTO.getDateFrom() != null){
            if (dateValidator.isValid( searchHotelDTO.getDateFrom() )) {
                searchHotelDatesDTO.setDateFrom(dateValidator.strToLocalDate( searchHotelDTO.getDateFrom() ));
            }
            else throw new DateException("Invalid Date From");
        }
        if (searchHotelDTO.getDateTo() != null){
            if (dateValidator.isValid( searchHotelDTO.getDateTo() )) {
                searchHotelDatesDTO.setDateTo(dateValidator.strToLocalDate( searchHotelDTO.getDateTo() ).plusDays( -1 ));
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
        ResponseHotelDTO responseHotelDTO = new ResponseHotelDTO();
        responseHotelDTO.setCode( 200 );
        responseHotelDTO.setStatus( "ok" );
        responseHotelDTO.setHotels(hotelRepo.getHotels( searchHotelDatesDTO ));
        return responseHotelDTO;

        //return hotelRepo.getHotels(searchHotelDTO);
    }
}
