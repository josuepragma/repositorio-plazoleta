package com.pragma.smallsquare.application.handler.order;

import com.pragma.smallsquare.application.dto.request.OrderDishRequest;
import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.exceptions.OrderStatusInProgressException;
import com.pragma.smallsquare.application.mapper.IOrderDishRequestMapper;
import com.pragma.smallsquare.application.mapper.IOrderRequestMapper;
import com.pragma.smallsquare.domain.api.IOrderServicePort;
import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.OrderDish;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.insfrastructure.util.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HU11MakeOrderTest {

    @InjectMocks
    private OrderHandler orderHandler;

    @Mock
    private IOrderServicePort orderServicePort;
    @Mock
    private IOrderRequestMapper orderRequestMapper;
    @Mock
    private IOrderDishRequestMapper orderDishRequestMapper;

    private Validator validator;
    private Order order;
    private List<OrderDish> orderDishList;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        OrderDish orderDish01 = new OrderDish(1, 100, 1, 3);
        OrderDish orderDish02 = new OrderDish(2, 100, 2, 1);
        OrderDish orderDish03 = new OrderDish(3, 100, 3, 1);
        orderDishList = List.of(orderDish01, orderDish02, orderDish03);

        order = new Order();
        order.setId(10);
        order.setOrderDate(new Date());
        order.setStatus(StatusEnum.PENDING.getName());
        order.setIdCustomer(40);
        order.setIdChef(30);
        order.setRestaurant(restaurant);
//        order.setOrdersDishes(orderDishList);
    }

    @Test
    @DisplayName("Make an ORDER with CORRECT values")
    void makeOrder_WithCorrectValues() {
        Integer currentCustomerId = 40;

        //  Given
        when(orderServicePort.getLastOrderByCustomerId(anyInt())).thenReturn(null);
        when(orderRequestMapper.toOrder(any(OrderRequest.class))).thenReturn(order);
        when(orderDishRequestMapper.toOrderDishList(anyList())).thenReturn(orderDishList);
        doNothing().when(orderServicePort).saveOrder(any(Order.class));

        //  When
        orderHandler.saveNewOrderDto(getOrderRequest_WithRightValues(), currentCustomerId);

        //  Then
        verify(orderServicePort).saveOrder(any(Order.class));
        verify(orderServicePort).getLastOrderByCustomerId(anyInt());
        verify(orderRequestMapper).toOrder(any(OrderRequest.class));
        verify(orderDishRequestMapper).toOrderDishList(anyList());
    }

    @Test
    @DisplayName("Make an ORDER but exists another Order in progress")
    void makeOrder_WithExceptionByAnotherOrderInProgress() {
        Integer currentCustomerId = 40;

        //  Given
        when(orderServicePort.getLastOrderByCustomerId(anyInt())).thenReturn(order);

        //  When
        //  Then
        assertThrows(OrderStatusInProgressException.class,
                () -> orderHandler.saveNewOrderDto(getOrderRequest_WithRightValues(), currentCustomerId));
    }


    //######################################
    //              METHODS
    //######################################
    OrderRequest getOrderRequest_WithRightValues() {
        OrderDishRequest orderDish01 = new OrderDishRequest(1, 3);
        OrderDishRequest orderDish02 = new OrderDishRequest(2, 1);
        OrderDishRequest orderDish03 = new OrderDishRequest(3, 1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdChef(30);
        orderRequest.setIdRestaurant(1);
        orderRequest.setOrdersDishes(List.of(orderDish01, orderDish02, orderDish03));

        return orderRequest;
    }
}
