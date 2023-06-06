package com.pragma.smallsquare.dish.infrastructure.configuration;

import com.pragma.smallsquare.category.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.category.domain.spi.ICategoryPersistencePort;
import com.pragma.smallsquare.category.domain.usecase.CategoryUseCase;
import com.pragma.smallsquare.category.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.smallsquare.category.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.smallsquare.category.infrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.smallsquare.dish.domain.api.IDishServicePort;
import com.pragma.smallsquare.dish.domain.spi.IDishPersistencePort;
import com.pragma.smallsquare.dish.domain.usecase.DishUseCase;
import com.pragma.smallsquare.dish.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.pragma.smallsquare.dish.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.smallsquare.dish.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DishBeanConfiguration {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
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
