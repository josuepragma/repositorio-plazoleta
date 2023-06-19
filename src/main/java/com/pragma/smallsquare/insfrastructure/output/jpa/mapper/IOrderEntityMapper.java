package com.pragma.smallsquare.insfrastructure.output.jpa.mapper;

import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {


    OrderEntity toOrderEntity(Order order);

    Order toOrder(OrderEntity orderEntity);

    List<Order> toOrderList(List<OrderEntity> orderEntityList);

}
