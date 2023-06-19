package com.pragma.smallsquare.application.handler.dish;

import com.pragma.smallsquare.application.dto.request.DishRequest;
import com.pragma.smallsquare.application.exceptions.UnauthorizedUserException;
import com.pragma.smallsquare.application.mapper.IDishRequestMapper;
import com.pragma.smallsquare.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.domain.api.IDishServicePort;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Category;
import com.pragma.smallsquare.domain.model.Dish;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.insfrastructure.exceptions.CategoryNotFoundException;
import com.pragma.smallsquare.insfrastructure.exceptions.RestaurantNotFoundException;
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
class HU03CreateDishTest {

    @InjectMocks
    private DishHandler dishHandler;

    @Mock
    private IDishServicePort dishServicePort;
    @Mock
    private IDishRequestMapper dishRequestMapper;
    @Mock
    private IRestaurantServicePort restaurantServicePort;
    @Mock
    private ICategoryServicePort categoryServicePort;

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
    @DisplayName("Create a Dish with CORRECT values")
    void createDish_WithCorrectValues() {
        //  Given
        Integer currentUserId = 10;
        when(dishRequestMapper.toDish(any(DishRequest.class))).thenReturn(dish);
        when(categoryServicePort.getCategoryById(anyInt())).thenReturn(category);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        dishHandler.saveDishDto(getDishRequest_WithRightValues(), currentUserId);

        //  Then
        verify(dishRequestMapper).toDish(any(DishRequest.class));
        verify(categoryServicePort).getCategoryById(anyInt());
        verify(restaurantServicePort).getRestaurantById(anyInt());
        verify(dishServicePort).saveDish(any(Dish.class));
    }

    @Test
    @DisplayName("Create a Dish with Unauthorized User")
    void createDish_WithUnauthorizedUserException() {
        //  Given
        Integer currentUserId = 9;
        when(dishRequestMapper.toDish(any(DishRequest.class))).thenReturn(dish);
        when(categoryServicePort.getCategoryById(anyInt())).thenReturn(category);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        //  Then
        assertThrows(UnauthorizedUserException.class, () -> dishHandler.saveDishDto(getDishRequest_WithRightValues(), currentUserId));
    }

    @Test
    @DisplayName("Create a Dish with Category Not Found Exception")
    void createDish_WithCategoryNotFoundException() {
        //  Given
        Integer currentUserId = 10;
        when(dishRequestMapper.toDish(any(DishRequest.class))).thenReturn(dish);
        when(categoryServicePort.getCategoryById(anyInt())).thenReturn(null);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(restaurant);

        //  When
        //  Then
        assertThrows(CategoryNotFoundException.class, () -> dishHandler.saveDishDto(getDishRequest_WithRightValues(), currentUserId));
    }

    @Test
    @DisplayName("Create a Dish with Restaurant Not Found Exception")
    void createDish_WithRestaurantNotFoundException() {
        //  Given
        Integer currentUserId = 10;
        when(dishRequestMapper.toDish(any(DishRequest.class))).thenReturn(dish);
        when(categoryServicePort.getCategoryById(anyInt())).thenReturn(category);
        when(restaurantServicePort.getRestaurantById(anyInt())).thenReturn(null);

        //  When
        //  Then
        assertThrows(RestaurantNotFoundException.class, () -> dishHandler.saveDishDto(getDishRequest_WithRightValues(), currentUserId));
    }

    @Test
    @DisplayName("Create Dish with RIGHT REQUEST")
    void createDish_WithRightRequest() {
        //  Given
        DishRequest dishRequest = getDishRequest_WithRightValues();

        //  When
        Set<ConstraintViolation<DishRequest>> violations = validator.validate(dishRequest);

        //  Then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Create Dish with BAD REQUEST")
    void createRestaurant_WithBadRequest() {
        //  Given
        DishRequest dishRequest = getDishRequest_WithBadValues();

        //  When
        Set<ConstraintViolation<DishRequest>> violations = validator.validate(dishRequest);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        //  Then
        assertEquals(6, violations.size());
        assertTrue(messages.contains("DISH NAME field is required"));
        assertTrue(messages.contains("DISH PRICE must be positive integer and greater than zero"));
        assertTrue(messages.contains("DISH DESCRIPTION field is required"));
        assertTrue(messages.contains("URL-IMAGE field is required"));
        assertTrue(messages.contains("ID CATEGORY field is required"));
        assertTrue(messages.contains("ID RESTAURANT field is required"));
    }

    //######################################
    //              METHODS
    //######################################
    DishRequest getDishRequest_WithRightValues() {
        DishRequest dishRequest = new DishRequest();
        dishRequest.setName("CAUSA RELLENA");
        dishRequest.setPrice(12);
        dishRequest.setDescription("description");
        dishRequest.setUrlImage("https://images.pexels.com/photos/causa-rellena.jpg");
        dishRequest.setIdCategory(1);
        dishRequest.setIdRestaurant(1);

        return dishRequest;
    }

    DishRequest getDishRequest_WithBadValues() {
        DishRequest dishRequest = new DishRequest();
        dishRequest.setName("");
        dishRequest.setPrice(-10);
        dishRequest.setDescription("");
        dishRequest.setUrlImage("");
        dishRequest.setIdCategory(null);
        dishRequest.setIdRestaurant(null);

        return dishRequest;
    }
}
