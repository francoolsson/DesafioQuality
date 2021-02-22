package com.example.demo.controller;


import com.example.demo.DTO.response.StatusDTO;
import com.example.demo.exceptions.BookingException;
import com.example.demo.exceptions.DateException;
import com.example.demo.exceptions.SearchHotelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DateException.class)
    public ResponseEntity<StatusDTO> handleException(DateException dateException){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode( 400 );
        statusDTO.setStatus( dateException.getMessage() );
        return new ResponseEntity<>( statusDTO, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(SearchHotelException.class)
    public ResponseEntity<StatusDTO> handleException(SearchHotelException searchHotelException){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode( 404 );
        statusDTO.setStatus( searchHotelException.getMessage() );
        return new ResponseEntity<>( statusDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BookingException.class)
    public ResponseEntity<StatusDTO> handleException(BookingException bookingException){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode( 400 );
        statusDTO.setStatus( bookingException.getMessage() );
        return new ResponseEntity<>( statusDTO, HttpStatus.BAD_REQUEST);
    }

}
