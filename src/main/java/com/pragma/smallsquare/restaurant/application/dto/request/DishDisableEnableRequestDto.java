package com.pragma.smallsquare.restaurant.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishDisableEnableRequestDto {

    @NotNull(message = "ACTIVE STATUS field is required. True or False")
    Boolean active;
}
