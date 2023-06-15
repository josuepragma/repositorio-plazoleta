package com.pragma.smallsquare.application.handler.dish;

import com.pragma.smallsquare.application.dto.request.DishModifyRequestDto;
import com.pragma.smallsquare.application.dto.request.DishRequestDto;
import com.pragma.smallsquare.application.exceptions.UnauthorizedUserException;
import com.pragma.smallsquare.domain.api.IDishServicePort;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Category;
import com.pragma.smallsquare.domain.model.Dish;
import com.pragma.smallsquare.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HU04ModifyDishTest {

    @InjectMocks
    private DishHandler dishHandler;
    @Mock
    private IDishServicePort dishServicePort;
    @Mock
    private IRestaurantServicePort restaurantServicePort;

    private Validator validator;
    private Dish dish;
    private Category category;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("RESTAURANT 123");
        restaurant.setAddress("1111111110111");
        restaurant.setPhone("+51999888777");
        restaurant.setUrlLogo("https://images.pexels.com/photos/restaurant.jpg");
        restaurant.setIdOwner(10);

        category = new Category();
        category.setId(1);
        category.setName("PLATO PRINCIPAL");
        category.setDescription("");

        dish = new Dish();
        dish.setId(1);
        dish.setName("CAUSA RELLENA");
        dish.setCategory(category);
        dish.setDescription("description");
        dish.setPrice(12);
        dish.setRestaurant(restaurant);
        dish.setUrlImage("https://images.pexels.com/photos/food.jpg");
        dish.setActive(true);

    }

    @Test
    @DisplayName("Modify PRICE and DESCRIPTION in a Dish")
    void updateDishFields_WithCorrectValues() {
        Integer dishId = 1;
        Integer currentUserId = 10;
        //  Given
        when(dishServicePort.getDishById(anyInt())).thenReturn(dish);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        dishHandler.updatePriceAndDescriptionDishDto(getDishRequest_WithRightValues(), dishId, currentUserId);

        //  Then
        verify(dishServicePort).updateDish(any(Dish.class));
        verify(dishServicePort).getDishById(anyInt());
        verify(restaurantServicePort).getRestaurantById(anyInt());

    }

    @Test
    @DisplayName("Modify PRICE and DESCRIPTION in a Dish with Unauthorized User")
    void updateDishFields_WithUnauthorizedUserException() {
        Integer dishId = 1;
        Integer currentUserId = 9;
        //  Given
        when(dishServicePort.getDishById(anyInt())).thenReturn(dish);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        //  Then
        assertThrows(UnauthorizedUserException.class,
                () -> dishHandler.updatePriceAndDescriptionDishDto(getDishRequest_WithRightValues(), dishId, currentUserId));

    }

    @Test
    @DisplayName("Modify PRICE and DESCRIPTION in a Dish with RIGHT REQUEST")
    void updateDishFields_WithRightRequest() {
        //  Given
        DishModifyRequestDto dishRequest = getDishRequest_WithRightValues();

        //  When
        Set<ConstraintViolation<DishModifyRequestDto>> violations = validator.validate(dishRequest);

        //  Then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Modify PRICE and DESCRIPTION in a Dish with BAD REQUEST")
    void updateDishFields_WithBadRequest() {
        //  Given
        DishModifyRequestDto dishRequest = getDishRequest_WithBadValues();

        //  When
        Set<ConstraintViolation<DishModifyRequestDto>> violations = validator.validate(dishRequest);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        //  Then
        assertEquals(2, violations.size());
        assertTrue(messages.contains("DISH PRICE field is required"));
        assertTrue(messages.contains("DISH DESCRIPTION field is required"));
    }

    //######################################
    //              METHODS
    //######################################
    DishModifyRequestDto getDishRequest_WithRightValues() {
        DishModifyRequestDto dishRequest = new DishModifyRequestDto();
        dishRequest.setPrice(12);
        dishRequest.setDescription("description");

        return dishRequest;
    }

    DishModifyRequestDto getDishRequest_WithBadValues() {
        DishModifyRequestDto dishRequest = new DishModifyRequestDto();
        dishRequest.setPrice(null);
        dishRequest.setDescription("");

        return dishRequest;
    }
}
