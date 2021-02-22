package com.example.demo.DTO.intern;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private String dni;
    private String name;
    private String surname;
    private String birthDate;
    private String mail;

}
