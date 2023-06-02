package com.pragma.smallsquare.restaurant.insfrastructure.input.rest;

import com.pragma.smallsquare.restaurant.application.dto.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.application.handler.IRestaurantHandler;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveRestaurantInSmallSquare(@Valid @RequestBody RestaurantRequestDto restaurantRequest) {
        restaurantHandler.saveRestaurantDto(restaurantRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurantsDto());
    }

    @GetMapping("/{nit}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByNit(@PathVariable(name = "nit") String nit) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantDtoByNit(nit));
    }

}
