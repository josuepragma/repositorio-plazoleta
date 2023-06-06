package com.pragma.smallsquare.dish.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DishRequestDto2Update {
    @NotEmpty(message = "DISH DESCRIPTION field is required.")
    String description;

    @NotNull(message = "DISH PRICE field is required.")
    @Positive(message = "DISH PRICE must be positive integer and greater than zero")
    Integer price;
}
