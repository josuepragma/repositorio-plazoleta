package com.pragma.smallsquare.insfrastructure.configuration;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.pragma.smallsquare.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.domain.api.IDishServicePort;
import com.pragma.smallsquare.domain.api.IOrderServicePort;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.api.employee.IEmployeeRestaurantServicePort;
import com.pragma.smallsquare.domain.spi.*;
import com.pragma.smallsquare.domain.spi.employee.IEmployeeRestaurantPersistencePort;
import com.pragma.smallsquare.domain.usecase.CategoryUseCase;
import com.pragma.smallsquare.domain.usecase.DishUseCase;
import com.pragma.smallsquare.domain.usecase.OrderUseCase;
import com.pragma.smallsquare.domain.usecase.RestaurantUseCase;
import com.pragma.smallsquare.domain.usecase.employee.EmployeeRestaurantUseCase;
import com.pragma.smallsquare.insfrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.smallsquare.insfrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.pragma.smallsquare.insfrastructure.output.jpa.adapter.employee.EmployeeRestaurantJpaAdapterRestaurant;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.employee.IEmployeeRestaurantEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.smallsquare.insfrastructure.output.jpa.adapter.DishJpaAdapter;
import com.pragma.smallsquare.insfrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.IDishRepository;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.employee.IEmployeeRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
    private final IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper, restaurantRepository, categoryRepository);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper, restaurantEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort());
    }


    @Bean
    public IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort() {
        return new EmployeeRestaurantJpaAdapterRestaurant(employeeRestaurantRepository, employeeRestaurantEntityMapper);
    }

    @Bean
    public IEmployeeRestaurantServicePort employeeRestaurantServicePort() {
        return new EmployeeRestaurantUseCase(employeeRestaurantPersistencePort());
    }

    @Bean
    public Filter TracingFilter() {
        return new AWSXRayServletFilter("Scorekeep");
    }

}
