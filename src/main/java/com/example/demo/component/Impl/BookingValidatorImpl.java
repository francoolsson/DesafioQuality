package com.example.demo.component.Impl;

import com.example.demo.DTO.intern.BookingDTO;
import com.example.demo.DTO.intern.PaymentDTO;
import com.example.demo.DTO.intern.PersonDTO;
import com.example.demo.component.BookingValidator;
import com.example.demo.exceptions.BookingException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;


public class BookingValidatorImpl implements BookingValidator {

    @Override
    public boolean mailValidator(String mail) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return mail.matches( regex );
    }

    @Override
    public boolean peopleQuantity(BookingDTO bookingDTO) {
        if((bookingDTO.getPeopleAmount()<1) || (bookingDTO.getPeople().isEmpty())) throw new BookingException("People amount equals 0");
        if (bookingDTO.getPeopleAmount() != bookingDTO.getPeople().size()) return false;
        return true;
    }

    @Override
    public boolean peopleValidator(List<PersonDTO> people) {
        for (PersonDTO personDTO:people){
            if (personDTO.getBirthDate()==null || personDTO.getSurname()==null
            || personDTO.getName()==null || personDTO.getDni()==null) return false;
        }
        return true;
    }

    @Override
    public boolean paymentValidator(PaymentDTO paymentDTO) {
        if (!(paymentDTO.getNumber().matches( "^[1-9][0-9]{3}(-[0-9]{4}){3}$" ))) return false;
        if (!(paymentDTO.getType().toLowerCase().matches( "credit" ))&&
                (paymentDTO.getType().toLowerCase().matches("debit" ))&&
                (paymentDTO.getType().toLowerCase().matches("cash"))) return false;
        if (paymentDTO.getType().toLowerCase().matches("credit")){
            if (paymentDTO.getDues()<1 || paymentDTO.getDues()>12) return false;}
        else {if (paymentDTO.getDues()!=1) return false;};
        return true;
    }
}
