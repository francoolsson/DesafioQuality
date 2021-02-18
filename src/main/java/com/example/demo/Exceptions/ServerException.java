package com.example.demo.Exceptions;

public class ServerException extends RuntimeException{
    public ServerException (){ super();}
    public ServerException (String message){super(message);}
}
