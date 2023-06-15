package com.pragma.smallsquare.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DishResponseDto {
    String name;
    String category;
    String description;
    Integer price;
    String restaurant;
    String urlImage;
    Boolean active;
}
