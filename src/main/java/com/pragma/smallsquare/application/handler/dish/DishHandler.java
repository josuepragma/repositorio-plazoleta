package com.pragma.smallsquare.application.handler.dish;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.pragma.smallsquare.application.dto.request.DishModifyRequest;
import com.pragma.smallsquare.application.dto.request.DishRequest;
import com.pragma.smallsquare.application.dto.response.DishResponse;
import com.pragma.smallsquare.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.domain.api.IDishServicePort;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Category;
import com.pragma.smallsquare.domain.model.Dish;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.insfrastructure.exceptions.CategoryNotFoundException;
import com.pragma.smallsquare.insfrastructure.exceptions.RestaurantNotFoundException;
import com.pragma.smallsquare.application.dto.request.DishDisableEnableRequest;
import com.pragma.smallsquare.application.mapper.IDishRequestMapper;
import com.pragma.smallsquare.application.mapper.IDishResponseMapper;
import com.pragma.smallsquare.application.exceptions.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@XRayEnabled
public class DishHandler implements IDishHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final ICategoryServicePort categoryServicePort;

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDishDto(DishRequest dishRequest, Integer currentUserId) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        Category category = categoryServicePort.getCategoryById(dishRequest.getIdCategory());
        Restaurant restaurant = restaurantServicePort.getRestaurantById(dishRequest.getIdRestaurant());

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
    public List<DishResponse> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory,
                                                                      int page, int size) {
        List<Dish> dishList = dishServicePort
                .getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size);

        return dishResponseMapper.toResponseDtoList(dishList);
    }

    @Override
    public DishResponse getDishDtoById(Integer id) {
        Dish dish = dishServicePort.getDishById(id);

        return dishResponseMapper.toResponseDto(dish);
    }

    @Override
    public void updatePriceAndDescriptionDishDto(DishModifyRequest dishRequest, Integer dishId, Integer currentUserId) {
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
    public void changeStatusDishDto(DishDisableEnableRequest dishRequest, Integer dishId, Integer currentUserId) {
        Dish updatedDish = dishServicePort.getDishById(dishId);
        Restaurant restaurant = restaurantServicePort.getRestaurantById(updatedDish.getRestaurant().getId());

        if (!Objects.equals(currentUserId, restaurant.getIdOwner())) {
            throw new UnauthorizedUserException("Current USER is NOT AUTHORIZED to CHANGE STATE in current DISH");
        }

        updatedDish.setActive(dishRequest.getActive());
        dishServicePort.updateDish(updatedDish);
    }
}
