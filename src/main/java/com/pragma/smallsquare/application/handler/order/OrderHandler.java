package com.pragma.smallsquare.application.handler.order;

import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.application.mapper.IOrderRequestMapper;
import com.pragma.smallsquare.application.mapper.IOrderResponseMapper;
import com.pragma.smallsquare.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;

    @Override
    public void saveOrderDto(OrderRequest orderRequest) {

    }

    @Override
    public OrderResponse getOrderDtoById(Integer id) {
        return null;
    }

    @Override
    public void updateOrderDto(OrderRequest orderRequest) {

    }
}
