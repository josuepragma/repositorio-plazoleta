package com.pragma.smallsquare.insfrastructure.input.rest;

import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.application.handler.order.IOrderHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/small-square")
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @PostMapping(value = "/order/")
    public ResponseEntity<Void> createNewOrderInRestaurant(@Valid @RequestBody OrderRequest orderRequest,
                                                           HttpServletRequest request) {

        log.warn("<--- FROM ORDER REST CONTROLLER --->");
        Integer currentCustomerId = Integer.valueOf(request.getAttribute("userId").toString());
        log.warn("<--- orderHandler.saveNewOrderDto(orderRequest, currentCustomerId) --->");
        orderHandler.saveNewOrderDto(orderRequest, currentCustomerId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/order/status/{statusName}/filter")
    public ResponseEntity<List<OrderResponse>> getAllOrdersFilteredByStatus(@PathVariable(value = "statusName") String status,
                                                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                                                            @RequestParam(value = "size", defaultValue = "10") int size,
                                                                            HttpServletRequest request) {
        Integer employeeUserId = Integer.valueOf(request.getAttribute("userId").toString());

        return ResponseEntity.ok(orderHandler
                .getAllOrdersFilteredByStatusAndRestaurant(status.toUpperCase(), page, size, employeeUserId));
    }
}
