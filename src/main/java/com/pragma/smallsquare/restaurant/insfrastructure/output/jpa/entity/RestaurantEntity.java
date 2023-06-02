package com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String address;
    String phone;
    String urlLogo;
    String nit;
    Integer idOwner;
}
