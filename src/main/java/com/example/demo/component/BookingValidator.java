package com.example.demo.component;

import com.example.demo.DTO.intern.BookingDTO;
import com.example.demo.DTO.intern.PersonDTO;

import java.util.List;

public interface BookingValidator {

    boolean mailValidator(String mail);
    boolean peopleQuantity (BookingDTO bookingDTO);
    boolean peopleValidator (List<PersonDTO> people);

}
