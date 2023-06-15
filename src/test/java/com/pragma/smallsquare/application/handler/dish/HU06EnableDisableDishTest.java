package com.pragma.smallsquare.application.handler.dish;

import com.pragma.smallsquare.application.dto.request.DishDisableEnableRequestDto;
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
class HU06EnableDisableDishTest {

    @InjectMocks
    private DishHandler dishHandler;

    @Mock
    private IDishServicePort dishServicePort;
    @Mock
    private IRestaurantServicePort restaurantServicePort;

    private Validator validator;
    private Dish dish;
    private Restaurant restaurant;
    private Category category;


    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("RESTAURANT 123");
        restaurant.setAddress("1111111110111");
        restaurant.setPhone("+51999888777");
        restaurant.setUrlLogo("https://images.pexels.com/photos/restaurant.jpg");
        restaurant.setIdOwner(20);

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
    @DisplayName("Enable o Disable STATUS of Dish")
    void changeStatusDish_WithCorrectValues() {
        Integer dishId = 1;
        Integer currentUserId = 20;

        //  Given
        when(dishServicePort.getDishById(anyInt())).thenReturn(dish);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        dishHandler.changeStatusDishDto(getDishRequest_WithRightValues(), dishId, currentUserId);

        //  Then
        verify(dishServicePort).updateDish(any(Dish.class));
        verify(dishServicePort).getDishById(anyInt());
        verify(restaurantServicePort).getRestaurantById(anyInt());
    }

    @Test
    @DisplayName("Enable o Disable STATUS of Dish with Unauthorized user")
    void changeStatusDish_WithUnauthorizedUser() {
        Integer dishId = 1;
        Integer currentUserId = 10;

        //  Given
        when(dishServicePort.getDishById(anyInt())).thenReturn(dish);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        //  Then
        assertThrows(UnauthorizedUserException.class,
                () -> dishHandler.changeStatusDishDto(getDishRequest_WithRightValues(), dishId, currentUserId));
    }

    @Test
    @DisplayName("Change STATUS of a Dish with RIGHT REQUEST")
    void changeStatusDish_WithRightRequest() {
        //  Given
        DishDisableEnableRequestDto dishRequest = getDishRequest_WithRightValues();

        //  When
        Set<ConstraintViolation<DishDisableEnableRequestDto>> violations = validator.validate(dishRequest);

        //  Then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Change STATUS of a Dish with BAD REQUEST")
    void changeStatusDish_WithBadRequest() {
        //  Given
        DishDisableEnableRequestDto dishRequest = getDishRequest_WithBadValues();

        //  When
        Set<ConstraintViolation<DishDisableEnableRequestDto>> violations = validator.validate(dishRequest);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        //  Then
        assertEquals(1, violations.size());
        assertTrue(messages.contains("ACTIVE field is required. True or False"));
    }


    //######################################
    //              METHODS
    //######################################
    DishDisableEnableRequestDto getDishRequest_WithRightValues() {
        DishDisableEnableRequestDto dishRequest = new DishDisableEnableRequestDto();
        dishRequest.setActive(false);

        return dishRequest;
    }

    DishDisableEnableRequestDto getDishRequest_WithBadValues() {
        DishDisableEnableRequestDto dishRequest = new DishDisableEnableRequestDto();
        dishRequest.setActive(null);

        return dishRequest;
    }
}
