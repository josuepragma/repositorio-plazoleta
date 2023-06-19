package com.pragma.smallsquare.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class OrderDishRequest {

    @NotNull(message = "ID DISH field is required")
    Integer idDish;

    @NotNull(message = "QUANTITY field is required")
    @Positive(message = "QUANTITY must be positive integer and greater than zero")
    Integer quantity;
}
