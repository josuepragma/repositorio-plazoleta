package com.pragma.smallsquare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderInPreparationRequest {

    @NotNull(message = "ID CHEF field is required")
    @Positive(message = "ID CHEF must be positive integer and greater than zero")
    private Integer idChef;

    @NotNull(message = "STATUS field is required")
    private String status;

}
