package com.pragma.smallsquare.insfrastructure.output.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "restaurant")
    private List<DishEntity> dishes;
}
