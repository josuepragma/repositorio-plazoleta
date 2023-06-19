package com.pragma.smallsquare.insfrastructure.input.rest;

import com.pragma.smallsquare.application.dto.request.DishModifyRequest;
import com.pragma.smallsquare.application.dto.request.DishRequest;
import com.pragma.smallsquare.application.dto.request.DishDisableEnableRequest;
import com.pragma.smallsquare.application.dto.response.DishResponse;
import com.pragma.smallsquare.application.handler.dish.IDishHandler;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/small-square")
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Save new Dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Dish created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/dish/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequest dishRequest, HttpServletRequest request) {
        Integer currentUserId = Integer.valueOf(request.getAttribute("userId").toString());
        dishHandler.saveDishDto(dishRequest, currentUserId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update Dish - Modify only price and description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT. Dish updated successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/dish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateDish(@Valid @RequestBody DishModifyRequest dishModifyRequest,
                                           @PathVariable(name = "id") Integer id,
                                           HttpServletRequest request) {
        Integer currentUserId = Integer.valueOf(request.getAttribute("userId").toString());
        dishHandler.updatePriceAndDescriptionDishDto(dishModifyRequest, id, currentUserId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Enable or Disable Dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT. Dish updated successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/dish/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> enableOrDisableDish(@Valid @RequestBody DishDisableEnableRequest dishRequest,
                                                    @PathVariable(name = "id") Integer id,
                                                    HttpServletRequest request) {
        Integer currentUserId = Integer.valueOf(request.getAttribute("userId").toString());
        dishHandler.changeStatusDishDto(dishRequest, id, currentUserId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all Dishes By Restaurant and Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Dishes found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DishResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Dishes not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/dish/restaurant/{idRestaurant}/category/{idCategory}/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishResponse>> getAllDishesByRestaurantAndCategory(
            @PathVariable(value = "idRestaurant") Integer idRestaurant,
            @PathVariable(value = "idCategory") Integer idCategory,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(dishHandler.getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size));
    }

    @Operation(summary = "Get Dish by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Dish found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DishResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Dish not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/dish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishResponse> getDishById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(dishHandler.getDishDtoById(id));
    }

}
