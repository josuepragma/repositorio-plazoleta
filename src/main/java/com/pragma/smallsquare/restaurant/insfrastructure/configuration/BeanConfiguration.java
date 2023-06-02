package com.pragma.smallsquare.restaurant.insfrastructure.configuration;

import com.pragma.smallsquare.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.smallsquare.restaurant.domain.usecase.RestaurantUseCase;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }

}
