package com.pragma.smallsquare.application.handler.employee;

import com.pragma.smallsquare.application.dto.request.EmployeeRequest;

public interface IEmployeeHandler {
    void saveEmployeeFromSmallSquare(EmployeeRequest employeeRequest, Integer currentOwnerId);

}
