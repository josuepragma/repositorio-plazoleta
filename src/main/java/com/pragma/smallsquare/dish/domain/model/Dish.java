package com.pragma.smallsquare.dish.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dish {
    Integer id;
    String name;
    Integer idCategory;
    String description;
    Integer price;
    Integer idRestaurant;
    String urlImage;
    Boolean active;
}
