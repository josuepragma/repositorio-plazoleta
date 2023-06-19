package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.request.OrderDishRequest;
import com.pragma.smallsquare.domain.model.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishRequestMapper {

//    @Mapping(source = "idDish", target = "idDish")
    OrderDish toOrderDish(OrderDishRequest orderDishRequest);

//    @Mapping(source = "idDish", target = "idDish")
    List<OrderDish> toOrderDishList(List<OrderDishRequest> orderDishRequestList);

}
