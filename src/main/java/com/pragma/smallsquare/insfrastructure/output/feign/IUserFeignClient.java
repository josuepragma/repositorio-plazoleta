package com.pragma.smallsquare.insfrastructure.output.feign;

import com.pragma.smallsquare.application.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-owner-api", url = "localhost:8090")
public interface IUserFeignClient {

    @GetMapping("/user/{id}")
    public UserResponse getUserById(@PathVariable Integer id);

}
