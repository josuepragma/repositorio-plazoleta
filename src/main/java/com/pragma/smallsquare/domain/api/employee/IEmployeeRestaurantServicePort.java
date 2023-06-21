package com.pragma.smallsquare.domain.api.employee;

import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;

public interface IEmployeeRestaurantServicePort {

    void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant);

    EmployeeRestaurant getEmployeeRestaurantByIdEmployee(Integer idEmployee);

    EmployeeRestaurant getEmployeeRestaurantByIdRestaurant(Integer idRestaurant);
}
