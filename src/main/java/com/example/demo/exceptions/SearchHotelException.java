package com.example.demo.exceptions;

public class SearchHotelException extends RuntimeException{
    public SearchHotelException(){ super();}
    public SearchHotelException(String message){super(message);}
}
