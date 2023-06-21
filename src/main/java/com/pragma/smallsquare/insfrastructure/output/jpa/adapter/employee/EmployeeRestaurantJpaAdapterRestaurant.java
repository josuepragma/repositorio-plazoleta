package com.pragma.smallsquare.insfrastructure.output.jpa.adapter.employee;

import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;
import com.pragma.smallsquare.domain.spi.employee.IEmployeeRestaurantPersistencePort;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.employee.EmployeeRestaurantEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.employee.IEmployeeRestaurantEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.employee.IEmployeeRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRestaurantJpaAdapterRestaurant implements IEmployeeRestaurantPersistencePort {

    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
    private final IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;

    @Override
    public void saveEmployeeRestaurant(EmployeeRestaurant employeeRestaurant) {
        EmployeeRestaurantEntity employeeRestaurantEntity = employeeRestaurantEntityMapper
                .toEmployeeRestaurantEntity(employeeRestaurant);

        employeeRestaurantRepository.save(employeeRestaurantEntity);
    }

    @Override
    public EmployeeRestaurant getEmployeeRestaurantByIdEmployee(Integer idEmployee) {
        EmployeeRestaurantEntity employeeRestaurantEntity = employeeRestaurantRepository
                .findByIdEmployee(idEmployee)
                .orElse(null);

        return employeeRestaurantEntityMapper.toEmployeeRestaurant(employeeRestaurantEntity);
    }

    @Override
    public EmployeeRestaurant getEmployeeRestaurantByIdRestaurant(Integer idRestaurant) {
        EmployeeRestaurantEntity employeeRestaurantEntity = employeeRestaurantRepository
                .findByIdRestaurant(idRestaurant)
                .orElse(null);

        return employeeRestaurantEntityMapper.toEmployeeRestaurant(employeeRestaurantEntity);
    }
}
