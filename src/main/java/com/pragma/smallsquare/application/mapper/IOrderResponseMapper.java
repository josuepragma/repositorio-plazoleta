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

    @Mapping(target = "restaurant", source = "order.restaurant.name")
    OrderResponse toOrderResponse(Order order);

    @Mapping(target = "restaurant", source = "order.restaurant.name")
    List<OrderResponse> toOrderResponseList(List<Order> orderList);

}
