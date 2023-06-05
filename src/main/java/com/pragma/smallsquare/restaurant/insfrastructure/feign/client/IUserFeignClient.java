package com.pragma.smallsquare.restaurant.insfrastructure.feign.client;

import com.pragma.smallsquare.restaurant.application.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-owner-api", url = "localhost:8090")
public interface IUserFeignClient {

    @GetMapping("/user/{id}")
    public UserResponseDto getOwnerUserById(@PathVariable Integer id);

}
