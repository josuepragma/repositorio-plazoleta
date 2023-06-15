package com.pragma.smallsquare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    Integer id;
    Date orderDate;
    String status;
    Integer idCustomer;
    Integer idChef;
    String restaurant;
}
