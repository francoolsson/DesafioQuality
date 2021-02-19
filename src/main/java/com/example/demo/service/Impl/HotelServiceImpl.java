package com.example.demo.service.Impl;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.SearchHotelDTO;
import com.example.demo.DTO.SearchHotelDatesDTO;
import com.example.demo.component.DateValidator;
import com.example.demo.component.Impl.DateValidatorImpl;
import com.example.demo.exceptions.DateException;
import com.example.demo.repository.HotelRepo;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    public HotelServiceImpl (@Autowired HotelRepo hotelRepo) {
        this.hotelRepo=hotelRepo;
        this.dateValidator= new DateValidatorImpl("dd/MM/yyyy");
    }
    private final HotelRepo hotelRepo;
    private final DateValidator dateValidator;


    @Override
    public List<HotelDTO> getHotels(SearchHotelDTO searchHotelDTO) {

        SearchHotelDatesDTO searchHotelDatesDTO = new SearchHotelDatesDTO();
        if (searchHotelDTO.getDateFrom() != null){
            if (dateValidator.isValid( searchHotelDTO.getDateFrom() )) {
                searchHotelDatesDTO.setDateFrom(dateValidator.strToLocalDate( searchHotelDTO.getDateFrom() ));
            }
            else throw new DateException("Invalid Date From");
        }
        if (searchHotelDTO.getDateTo() != null){
            if (dateValidator.isValid( searchHotelDTO.getDateTo() )) {
                searchHotelDatesDTO.setDateTo(dateValidator.strToLocalDate( searchHotelDTO.getDateTo() ));
            }
            else throw new DateException("Invalid Date To");
        }
        searchHotelDatesDTO.setDestination( searchHotelDTO.getDestination() );
        return hotelRepo.getHotels( searchHotelDatesDTO );

        //return hotelRepo.getHotels(searchHotelDTO);
    }
}
