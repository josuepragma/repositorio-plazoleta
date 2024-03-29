package com.pragma.smallsquare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String name;
    private String surname;
    private String documentNumber;
    private String phone;
    private String birthdate;
    private String email;
    private String password;
    private Integer idRole;
    private String nameRole;
}
