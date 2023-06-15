package com.pragma.smallsquare.application.dto.request;

import com.pragma.smallsquare.domain.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "ID CUSTOMER field is required")
    @Positive(message = "ID CUSTOMER must be positive integer and greater than zero")
    private Integer idCustomer;

    @NotNull(message = "ID CHEF field is required")
    @Positive(message = "ID CHEF must be positive integer and greater than zero")
    private Integer idChef;

    @NotNull(message = "ID RESTAURANT field is required")
    @Positive(message = "ID RESTAURANT must be positive integer and greater than zero")
    private Integer idRestaurant;
}
