package com.pragma.smallsquare.insfrastructure.input.rest;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.pragma.smallsquare.application.dto.request.OrderReadyRequest;
import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.request.OrderInPreparationRequest;
import com.pragma.smallsquare.application.dto.response.DishResponse;
import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.application.handler.order.IOrderHandler;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/small-square")
@XRayEnabled
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @Operation(summary = "Create new Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Order created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/order/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewOrderInRestaurant(@Valid @RequestBody OrderRequest orderRequest,
                                                           HttpServletRequest request) {

        Integer currentCustomerId = Integer.valueOf(request.getAttribute("userId").toString());
        orderHandler.saveNewOrderDto(orderRequest, currentCustomerId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List all Orders Filtered By Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Order List found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DishResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Orders not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/order/status/{statusName}/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponse>> getAllOrdersFilteredByStatus(@PathVariable(value = "statusName") String status,
                                                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                                                            @RequestParam(value = "size", defaultValue = "10") int size,
                                                                            HttpServletRequest request) {
        Integer employeeUserId = Integer.valueOf(request.getAttribute("userId").toString());

        return ResponseEntity.ok(orderHandler
                .getAllOrdersFilteredByStatusAndRestaurant(status.toUpperCase(), page, size, employeeUserId));
    }

    @Operation(summary = "Update Order - Change Status From Pending to In Preparation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Order updated successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrderInPreparationStatus(@Valid @RequestBody OrderInPreparationRequest orderRequest,
                                                               @PathVariable(name = "id") Integer idOrder,
                                                               HttpServletRequest request) {

        Integer currentCustomerId = Integer.valueOf(request.getAttribute("userId").toString());
        orderHandler.updateOrderInPreparationStatus(orderRequest, idOrder, currentCustomerId);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update Order - Change Status From In Preparation to Ready")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Order updated successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/order/ready/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrderReadyStatus(@Valid @RequestBody OrderReadyRequest orderRequest,
                                                       @PathVariable(name = "id") Integer idOrder,
                                                       HttpServletRequest request) {

        Integer currentCustomerId = Integer.valueOf(request.getAttribute("userId").toString());
        orderHandler.updateOrderReadyStatus(orderRequest, idOrder, currentCustomerId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/josue")
    public ResponseEntity<Object> getMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "JOSUE");
        map.put("message", "It works!!!");
        map.put("version", "Review");
        map.put("status", "Successfully");

        return ResponseEntity.ok(map);
    }
}
