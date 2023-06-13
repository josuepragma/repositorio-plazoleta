package com.pragma.smallsquare.restaurant.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {
    Integer id;
    String name;
    String category;
    String description;
    Integer price;
    String restaurant;
    String urlImage;
    Boolean active;
}
