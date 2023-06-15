package com.pragma.smallsquare.application.handler.order;

import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.domain.model.Order;

public interface IOrderHandler {
    void saveOrderDto(OrderRequest orderRequest);

    OrderResponse getOrderDtoById(Integer id);

    void updateOrderDto(OrderRequest orderRequest);
}
