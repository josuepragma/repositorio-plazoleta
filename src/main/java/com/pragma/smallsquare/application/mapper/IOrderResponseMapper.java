package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

//    OrderResponse toOrderResponse(Order order);

//    List<OrderResponse> toOrderResponseList(List<Order> orderList);

}
