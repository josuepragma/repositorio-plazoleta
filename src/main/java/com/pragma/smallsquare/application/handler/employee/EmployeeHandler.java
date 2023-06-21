package com.pragma.smallsquare.application.handler.employee;

import com.pragma.smallsquare.application.dto.request.EmployeeRequest;
import com.pragma.smallsquare.application.dto.response.UserResponse;
import com.pragma.smallsquare.application.exceptions.UserIsNoOwnerException;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.api.employee.IEmployeeRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;
import com.pragma.smallsquare.insfrastructure.output.feign.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeHandler implements IEmployeeHandler{

    private final IEmployeeRestaurantServicePort employeeRestaurantServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IUserFeignClient userFeignClient;

    @Override
    public void saveEmployeeFromSmallSquare(EmployeeRequest employeeRequest, Integer currentOwnerId) {

        UserResponse employee = userFeignClient.saveEmployeeFromSmallSquare(employeeRequest);

        Integer restaurantId = restaurantServicePort.getRestaurantByIdOwner(currentOwnerId).getId();

        EmployeeRestaurant employeeRestaurant = new EmployeeRestaurant();
        employeeRestaurant.setIdEmployee(employee.getId());
        employeeRestaurant.setIdRestaurant(restaurantId);

        employeeRestaurantServicePort.saveEmployeeRestaurant(employeeRestaurant);
    }
}
