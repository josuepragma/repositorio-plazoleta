package com.pragma.smallsquare.domain.model.employee;

public class EmployeeRestaurant {
    Integer id;
    Integer idEmployee;
    Integer idRestaurant;

    public EmployeeRestaurant() {
    }

    public EmployeeRestaurant(Integer id, Integer idEmployee, Integer idRestaurant) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idRestaurant = idRestaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
