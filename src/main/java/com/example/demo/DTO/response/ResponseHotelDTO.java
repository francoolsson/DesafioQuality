package com.example.demo.DTO.response;

import com.example.demo.DTO.intern.HotelDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHotelDTO {

    private String status;
    private Integer code;
    private List<HotelDTO> hotels;


}
