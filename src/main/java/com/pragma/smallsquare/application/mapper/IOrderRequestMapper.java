package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

//    Order toOrder(OrderRequest orderRequest);

}
