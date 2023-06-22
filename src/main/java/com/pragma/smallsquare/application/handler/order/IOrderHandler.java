package com.pragma.smallsquare.application.handler.order;

import com.pragma.smallsquare.application.dto.request.OrderInPreparationRequest;
import com.pragma.smallsquare.application.dto.request.OrderReadyRequest;
import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.response.OrderResponse;

import java.util.List;

public interface IOrderHandler {
    void saveNewOrderDto(OrderRequest orderRequest, Integer currentCustomerId);

    List<OrderResponse> getAllOrdersFilteredByStatusAndRestaurant(String status, int page, int size, Integer employeeUserId);

    void updateOrderInPreparationStatus(OrderInPreparationRequest orderRequest, Integer orderId, Integer currentEmployeeId);

    void updateOrderReadyStatus(OrderReadyRequest orderRequest, Integer orderId, Integer currentEmployeeId);

}
