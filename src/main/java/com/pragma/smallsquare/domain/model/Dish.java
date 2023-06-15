package com.pragma.smallsquare.domain.model;

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
    Category category;
    String description;
    Integer price;
    Restaurant restaurant;
    String urlImage;
    Boolean active;
}
