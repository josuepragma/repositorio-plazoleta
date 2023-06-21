package com.pragma.smallsquare.application.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    Integer id;
    Date orderDate;
    String status;
    Integer idCustomer;
    Integer idChef;
    String restaurant;
    List<OrderDishResponse> orderDishResponse;
}
