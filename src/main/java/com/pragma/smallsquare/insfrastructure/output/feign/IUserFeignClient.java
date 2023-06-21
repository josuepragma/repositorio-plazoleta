package com.pragma.smallsquare.insfrastructure.output.feign;

import com.pragma.smallsquare.application.dto.request.EmployeeRequest;
import com.pragma.smallsquare.application.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "user-owner-api", url = "localhost:8090")
public interface IUserFeignClient {

    @GetMapping("/user/{id}")
    public UserResponse getUserById(@PathVariable(name = "email") Integer id);

    @GetMapping("user/{email}/")
    public Integer getUserIdByEmail(@PathVariable(name = "email") String email);

    @PostMapping("/user/employee/")
    public UserResponse saveEmployeeFromSmallSquare(@Valid @RequestBody EmployeeRequest employeeRequest);

}
