package com.pragma.smallsquare.insfrastructure.output.jpa.repository.employee;

import com.pragma.smallsquare.insfrastructure.output.jpa.entity.employee.EmployeeRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, Integer> {
    Optional<EmployeeRestaurantEntity> findByIdEmployee(Integer idEmployee);

    Optional<EmployeeRestaurantEntity> findByIdRestaurant(Integer idRestaurant);
}
