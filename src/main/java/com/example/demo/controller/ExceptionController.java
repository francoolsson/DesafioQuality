package com.example.demo.controller;


import com.example.demo.DTO.response.ErrorDTO;
import com.example.demo.DTO.response.ResponseHotelDTO;
import com.example.demo.exceptions.DateException;
import com.example.demo.exceptions.SearchHotelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DateException.class)
    public ResponseEntity<ErrorDTO> handleException(DateException dateException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode( 400 );
        errorDTO.setStatus( dateException.getMessage() );
        return new ResponseEntity<>( errorDTO, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(SearchHotelException.class)
    public ResponseEntity<ResponseHotelDTO> handleException(SearchHotelException searchHotelException){
        ResponseHotelDTO responseHotelDTO = new ResponseHotelDTO();
        responseHotelDTO.setCode( 404 );
        responseHotelDTO.setStatus( searchHotelException.getMessage() );
        return new ResponseEntity<>( responseHotelDTO, HttpStatus.NOT_FOUND);
    }

}
