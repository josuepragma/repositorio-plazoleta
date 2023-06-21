package com.pragma.smallsquare.domain.spi.employee;

import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;

public interface IEmployeeRestaurantPersistencePort {
    void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant);

    EmployeeRestaurant getEmployeeRestaurantByIdEmployee(Integer idEmployee);

    EmployeeRestaurant getEmployeeRestaurantByIdRestaurant(Integer idRestaurant);
}
