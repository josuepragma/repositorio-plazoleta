package com.pragma.smallsquare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Integer idChef;

    @NotNull(message = "ID RESTAURANT field is required")
    @Positive(message = "ID RESTAURANT must be positive integer and greater than zero")
    private Integer idRestaurant;

    @NotNull(message = "ORDER_DISH must have one Order at least")
    private List<OrderDishRequest> ordersDishes;
}
