package com.example.demo.component.Impl;

import com.example.demo.component.DateValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Component
public class DateValidatorImpl implements DateValidator {


    DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern( "dd/MM/yyyy" );


    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public LocalDate strToLocalDate(String dateStr) {
        return LocalDate.parse( dateStr,this.dateTimeFormatter );
    }
}
