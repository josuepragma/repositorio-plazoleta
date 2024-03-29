package com.pragma.smallsquare.insfrastructure.output.jpa.adapter;

import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.domain.spi.IOrderPersistencePort;
import com.pragma.smallsquare.insfrastructure.exceptions.OrderNotFoundException;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.OrderEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toOrderEntity(order);

        for (OrderDishEntity item : orderEntity.getOrdersDishes()) {
            item.setOrder(orderEntity);
        }
        orderRepository.save(orderEntity);
    }

    @Override
    public Order getLastOrderByCustomerId(Integer customerId) {
        Pageable pageable = PageRequest.of(0, 1);

        if (orderRepository.findLastOrderByCustomerId(customerId, pageable).isEmpty()) {
            return null;
        }

        return orderEntityMapper.toOrder(orderRepository.findLastOrderByCustomerId(customerId, pageable).get(0));
    }

    @Override
    public List<Order> getAllOrdersFilteredByStatusAndRestaurant(String status, int page, int size, Restaurant restaurant) {
        Pageable pageable = PageRequest.of(page, size);

        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);

        List<OrderEntity> orderEntityList = orderRepository.findAllByStatusAndRestaurant(status, restaurantEntity, pageable);
        return orderEntityMapper.toOrderList(orderEntityList);
    }

    @Override
    public void updateEmployeeIdAndStatus(Order order) {

    }

    @Override
    public Order getOrderById(Integer idOrder) {

        OrderEntity orderEntity = orderRepository.findById(idOrder)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found with id = " + idOrder));

        return orderEntityMapper.toOrder(orderEntity);
    }

}
