package com.example.demo.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private String hotelCode;
    private String name;
    private String location;
    private String roomType;
    private Integer pricePerNight;
    private Boolean reserved;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "es_AR", timezone = "America/Argentina/Buenos_Aires")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate availableFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",locale = "es_AR", timezone = "America/Argentina/Buenos_Aires")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate availableUntil;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HotelDTO)) return false;
        HotelDTO hotel = (HotelDTO) obj;
        return this.getHotelCode().equals( hotel.getHotelCode())
                && this.getName().equals( hotel.getName() )
                && this.getLocation().equals( hotel.getLocation() );
    }

}
