package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

//    @Mapping(source = "order.orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "order.restaurant.name", target = "restaurant")
    @Mapping(source = "order.ordersDishes", target = "orderDishResponse")
    OrderResponse toOrderResponse(Order order);

//    @Mapping(source = "order.orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "order.restaurant.name", target = "restaurant")
    @Mapping(source = "order.ordersDishes", target = "orderDishResponse")
    List<OrderResponse> toOrderResponseList(List<Order> orderList);

}
