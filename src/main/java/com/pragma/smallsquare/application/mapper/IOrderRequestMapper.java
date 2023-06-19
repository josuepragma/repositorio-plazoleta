package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

    default Order toOrder(OrderRequest orderRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(orderRequest.getIdRestaurant());

        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setIdChef(orderRequest.getIdChef());

        return order;
    }
}
