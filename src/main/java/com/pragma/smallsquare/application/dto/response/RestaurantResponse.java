package com.pragma.smallsquare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {
    String name;
    String nit;
    String address;
    String phone;
    String urlLogo;
    Integer idOwner;
}
