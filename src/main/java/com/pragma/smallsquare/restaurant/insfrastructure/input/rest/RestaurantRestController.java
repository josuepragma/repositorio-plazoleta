package com.pragma.smallsquare.restaurant.insfrastructure.input.rest;

import com.pragma.smallsquare.restaurant.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.response.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.application.handler.restaurant.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/small-square")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Save new Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Restaurant created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/restaurant/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveRestaurantInSmallSquare(@Valid @RequestBody RestaurantRequestDto restaurantRequest) {
        restaurantHandler.saveRestaurantDto(restaurantRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List all Restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Restaurants found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestaurantResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Restaurants not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/restaurant/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurantsDto());
    }

    @Operation(summary = "Get Restaurant by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Restaurant found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestaurantResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Restaurant not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/restaurant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantDtoById(id));
    }

}
