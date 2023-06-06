package com.pragma.smallsquare.dish.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {
    String name;
    String category;
//    Integer idCategory;
    String description;
    Integer price;
//    String restaurant;
    Integer idRestaurant;
    String urlImage;
    Boolean active;
}
