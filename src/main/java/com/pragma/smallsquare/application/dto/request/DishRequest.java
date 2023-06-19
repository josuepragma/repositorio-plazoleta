package com.pragma.smallsquare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DishRequest {
    @NotEmpty(message = "DISH NAME field is required")
    String name;

    @NotNull(message = "DISH PRICE field is required")
    @Positive(message = "DISH PRICE must be positive integer and greater than zero")
    Integer price;

    @NotEmpty(message = "DISH DESCRIPTION field is required")
    String description;

    @NotEmpty(message = "URL-IMAGE field is required")
    String urlImage;

    @NotNull(message = "ID CATEGORY field is required")
    Integer idCategory;

    @NotNull(message = "ID RESTAURANT field is required")
    Integer idRestaurant;
}
