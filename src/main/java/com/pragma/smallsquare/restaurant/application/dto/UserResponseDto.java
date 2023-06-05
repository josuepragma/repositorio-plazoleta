package com.pragma.smallsquare.restaurant.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Integer id;
    private String name;
    private String surname;
    private String documentNumber;
    private String phone;
    private String birthdate;
    private String email;
    private String password;
    private String role;
}
