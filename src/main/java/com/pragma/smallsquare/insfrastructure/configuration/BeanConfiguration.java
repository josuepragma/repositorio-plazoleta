package com.pragma.smallsquare.restaurant.insfrastructure.configuration;

import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.smallsquare.restaurant.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.restaurant.domain.api.IDishServicePort;
import com.pragma.smallsquare.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.restaurant.domain.spi.ICategoryPersistencePort;
import com.pragma.smallsquare.restaurant.domain.spi.IDishPersistencePort;
import com.pragma.smallsquare.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.smallsquare.restaurant.domain.usecase.CategoryUseCase;
import com.pragma.smallsquare.restaurant.domain.usecase.DishUseCase;
import com.pragma.smallsquare.restaurant.domain.usecase.RestaurantUseCase;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.adapter.DishJpaAdapter;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.IDishRepository;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

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

}
