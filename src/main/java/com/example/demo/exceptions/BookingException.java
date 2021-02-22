package com.example.demo.exceptions;

public class BookingException extends RuntimeException{
    public BookingException(){ super();}
    public BookingException(String message){super(message);}
}
