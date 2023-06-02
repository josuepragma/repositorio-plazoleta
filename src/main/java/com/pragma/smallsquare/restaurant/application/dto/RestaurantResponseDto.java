package com.pragma.smallsquare.restaurant.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {
    String name;
    String nit;
    String address;
    String phone;
    String urlLogo;
    Integer idOwner;
}
