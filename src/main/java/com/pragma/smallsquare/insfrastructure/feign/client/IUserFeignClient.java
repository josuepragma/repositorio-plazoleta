package com.pragma.smallsquare.insfrastructure.feign.client;

import com.pragma.smallsquare.application.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-owner-api", url = "localhost:8090")
public interface IUserFeignClient {

    @GetMapping("/user/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id);

}
