package com.pragma.smallsquare.restaurant.application.handler.dish;

import com.pragma.smallsquare.restaurant.application.dto.request.DishDisableEnableRequestDto;
import com.pragma.smallsquare.restaurant.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Category;
import com.pragma.smallsquare.restaurant.application.dto.request.DishRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.request.DishModifyRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.response.DishResponseDto;
import com.pragma.smallsquare.restaurant.application.mapper.IDishRequestMapper;
import com.pragma.smallsquare.restaurant.application.mapper.IDishResponseMapper;
import com.pragma.smallsquare.restaurant.domain.api.IDishServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Dish;
import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.CategoryNotFoundException;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.RestaurantNotFoundException;
import com.pragma.smallsquare.restaurant.application.exceptions.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final ICategoryServicePort categoryServicePort;

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDishDto(DishRequestDto dishRequestDto, Integer currentUserId) {
        Dish dish = dishRequestMapper.toDish(dishRequestDto);
        Category category = categoryServicePort.getCategoryById(dishRequestDto.getIdCategory());
        Restaurant restaurant = restaurantServicePort.getRestaurantById(dishRequestDto.getIdRestaurant());

        if (category == null) {
            throw new CategoryNotFoundException("ID CATEGORY does not exist");
        }

        if (restaurant == null) {
            throw new RestaurantNotFoundException("ID RESTAURANT does not exist");
        }

        if (!Objects.equals(currentUserId, restaurant.getIdOwner())) {
            throw new UnauthorizedUserException("Current USER is NOT AUTHORIZED to CREATE a DISH in this RESTAURANT");
        }

        dish.setCategory(category);
        dish.setRestaurant(restaurant);
        dish.setActive(true);

        dishServicePort.saveDish(dish);
    }

    @Override
    public List<DishResponseDto> getAllDishesDto() {
        return dishResponseMapper.toResponseDtoList(dishServicePort.getAllDishes());
    }

    @Override
    public DishResponseDto getDishDtoById(Integer id) {
        Dish dish = dishServicePort.getDishById(id);

        return dishResponseMapper.toResponseDto(dish);
    }

    @Override
    public DishResponseDto getDishDtoByName(String name) {
        Dish dish = dishServicePort.getDishByName(name);

        return dishResponseMapper.toResponseDto(dish);
    }

    @Override
    public void updatePriceAndDescriptionDishDto(DishModifyRequestDto dishRequest, Integer dishId, Integer currentUserId) {
        Dish updatedDish = dishServicePort.getDishById(dishId);
        Restaurant restaurant = restaurantServicePort.getRestaurantById(updatedDish.getRestaurant().getId());

        if(!Objects.equals(restaurant.getIdOwner(), currentUserId)) {
            throw new UnauthorizedUserException("Current USER is NOT AUTHORIZED to MODIFY current DISH");
        }

        updatedDish.setDescription(dishRequest.getDescription());
        updatedDish.setPrice(dishRequest.getPrice());

        dishServicePort.updateDish(updatedDish);
    }

    @Override
    public void changeStatusDishDto(DishDisableEnableRequestDto dishRequest, Integer dishId, Integer currentUserId) {
        Dish updatedDish = dishServicePort.getDishById(dishId);
        Restaurant restaurant = restaurantServicePort.getRestaurantById(updatedDish.getRestaurant().getId());

        if (!Objects.equals(currentUserId, restaurant.getIdOwner())) {
            throw new UnauthorizedUserException("Current USER is NOT AUTHORIZED to CHANGE STATE in current DISH");
        }

        updatedDish.setActive(dishRequest.getActive());
        dishServicePort.updateDish(updatedDish);
    }

    @Override
    public void deleteDishDtoById(Integer id) {
        dishServicePort.deleteDishById(id);
    }
}
