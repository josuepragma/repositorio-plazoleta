package com.pragma.smallsquare.restaurant.insfrastructure.input.rest;

import com.pragma.smallsquare.restaurant.application.dto.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.application.dto.UserResponseDto;
import com.pragma.smallsquare.restaurant.application.handler.IRestaurantHandler;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.UserIsNoOwnerException;
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

        UserResponseDto owner = restaurantHandler.getOwnerUser(restaurantRequest.getIdOwner());
        if(!owner.getRole().equals("Propietario")) {
            throw new UserIsNoOwnerException("IdOwner does not belong an owner user");
        }

        restaurantHandler.saveRestaurantDto(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurantsDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantDtoById(id));
    }

}
