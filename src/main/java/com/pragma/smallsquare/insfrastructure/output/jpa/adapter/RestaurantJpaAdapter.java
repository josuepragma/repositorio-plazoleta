package com.pragma.smallsquare.insfrastructure.output.jpa.adapter;

import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.smallsquare.insfrastructure.exceptions.NoDataFoundException;
import com.pragma.smallsquare.insfrastructure.exceptions.RestaurantNotFoundException;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.smallsquare.insfrastructure.exceptions.RestaurantAlreadyExistsException;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()) {
            throw new RestaurantAlreadyExistsException("Restaurant already exist with NIT: " + restaurant.getNit());
        }

        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurantsOrderByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAllByOrderByName(pageable);

        if (restaurantEntityList.isEmpty()) {
            throw new NoDataFoundException("There is no restaurants in page number : " + page);
        }

        return restaurantEntityMapper.toRestaurantList(restaurantEntityList);
    }

    @Override
    public Restaurant getRestaurantById(Integer id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant searched doesn't exist with ID = " + id));

        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }
}
