package com.pragma.smallsquare.insfrastructure.output.jpa.mapper.employee;

import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.employee.EmployeeRestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRestaurantEntityMapper {
    EmployeeRestaurantEntity toEmployeeRestaurantEntity(EmployeeRestaurant employeeRestaurant);

    EmployeeRestaurant toEmployeeRestaurant(EmployeeRestaurantEntity employeeRestaurantEntity);
}
