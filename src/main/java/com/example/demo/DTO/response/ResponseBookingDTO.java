package com.example.demo.DTO.response;

import com.example.demo.DTO.intern.BookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBookingDTO {

    private String userName;
    private Long totalDays;
    private Double amount;
    private Double interest;
    private Double total;
    private BookingDTO booking;
    private StatusDTO statusCode;

}
