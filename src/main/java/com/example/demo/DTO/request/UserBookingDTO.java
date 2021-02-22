package com.example.demo.DTO.request;

import com.example.demo.DTO.intern.BookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBookingDTO {

    private String userName;
    private BookingDTO booking;

}
