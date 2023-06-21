package com.pragma.smallsquare.domain.usecase.employee;

import com.pragma.smallsquare.domain.api.employee.IEmployeeRestaurantServicePort;
import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;
import com.pragma.smallsquare.domain.spi.employee.IEmployeeRestaurantPersistencePort;

public class EmployeeRestaurantUseCase implements IEmployeeRestaurantServicePort {

    private final IEmployeeRestaurantPersistencePort employeePersistencePort;

    public EmployeeRestaurantUseCase(IEmployeeRestaurantPersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant) {
        employeePersistencePort.saveEmployeeRestaurant(employeeRestaurant);
    }

    @Override
    public EmployeeRestaurant getEmployeeRestaurantByIdEmployee(Integer idEmployee) {
        return employeePersistencePort.getEmployeeRestaurantByIdEmployee(idEmployee);
    }

    @Override
    public EmployeeRestaurant getEmployeeRestaurantByIdRestaurant(Integer idRestaurant) {
        return employeePersistencePort.getEmployeeRestaurantByIdRestaurant(idRestaurant);
    }
}
