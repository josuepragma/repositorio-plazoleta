package com.pragma.smallsquare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishDisableEnableRequest {

    @NotNull(message = "ACTIVE field is required. True or False")
    Boolean active;
}
