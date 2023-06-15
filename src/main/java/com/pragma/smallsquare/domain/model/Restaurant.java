package com.pragma.smallsquare.restaurant.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    Integer id;
    String name;
    String address;
    String phone;
    String urlLogo;
    String nit;
    Integer idOwner;
}