package com.pragma.smallsquare.insfrastructure.util;

public enum RoleEnum {
    ADMIN("ADMINISTRADOR"),
    OWNER("PROPIETARIO"),
    EMPLOYEE("EMPLEADO"),
    CUSTOMER("CLIENTE");

    private final String name;
    RoleEnum(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
