package com.example.demo.component.Impl;

import com.example.demo.component.DateValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateValidatorImpl implements DateValidator {

    private DateTimeFormatter dateTimeFormatter;

    public DateValidatorImpl (String strForm){

        this.dateTimeFormatter=DateTimeFormatter.ofPattern( strForm );
    }

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
