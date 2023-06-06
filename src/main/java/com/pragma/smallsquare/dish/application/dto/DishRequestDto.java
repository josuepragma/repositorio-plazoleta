package com.pragma.smallsquare.dish.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DishRequestDto {
    @NotEmpty(message = "DISH NAME field is required.")
    String name;

    @NotNull(message = "DISH PRICE field is required.")
//    @Min(value = 1, message = "DISH PRICE must be greater than zero")
    @Positive(message = "DISH PRICE must be positive integer and greater than zero")
    Integer price;

    @NotEmpty(message = "DISH DESCRIPTION field is required.")
    String description;

    @NotEmpty(message = "URL-IMAGE field is required.")
    String urlImage;

    @NotEmpty(message = "CATEGORY field is required.")
    String category;
}
