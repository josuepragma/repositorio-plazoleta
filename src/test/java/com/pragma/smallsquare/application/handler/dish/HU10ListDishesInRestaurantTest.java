package com.pragma.smallsquare.application.handler.dish;

import com.pragma.smallsquare.application.dto.response.DishResponseDto;
import com.pragma.smallsquare.application.mapper.IDishResponseMapper;
import com.pragma.smallsquare.domain.api.IDishServicePort;
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

import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HU10ListDishesInRestaurantTest {

    @InjectMocks
    private DishHandler dishHandler;

    @Mock
    private IDishServicePort dishServicePort;
    @Mock
    private IDishResponseMapper dishResponseMapper;

    private Restaurant restaurant;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(4, "PLATO PRINCIPAL", "");

        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("LA LUCHA");
    }

    @Test
    @DisplayName("List all Dishes in Restaurant Filter By Category")
    void getDishesInRestaurant_FilteredByCategory() {
        Integer idRestaurant = 1;
        Integer idCategory = 4;
        int page = 0;
        int size = 5;

        //  Given
        when(dishServicePort.getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size))
                .thenReturn(getDishList());
        when(dishResponseMapper.toResponseDtoList(anyList())).thenReturn(getDishResponseList());

        //  When
        List<DishResponseDto> dishes = dishHandler.getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size);

        //  Then
        assertEquals(3, dishes.size());
        assertEquals("ARROZ CON POLLO", dishes.get(2).getName());
        assertEquals(10, dishes.get(0).getPrice());

        verify(dishServicePort).getAllDishesByIdRestaurantAndIdCategory(anyInt(), anyInt(), anyInt(), anyInt());
        verify(dishResponseMapper).toResponseDtoList(anyList());
    }

    @Test
    @DisplayName("List empty Dish List in Restaurant Filter By Category")
    void getEmptyDishesInRestaurant_FilteredByCategory() {
        List<Dish> dishList = Collections.emptyList();
        List<DishResponseDto> dishResponseList = Collections.emptyList();

        Integer idRestaurant = 1;
        Integer idCategory = 4;
        int page = 0;
        int size = 5;

        //  Given
        when(dishServicePort.getAllDishesByIdRestaurantAndIdCategory(anyInt(), anyInt(), anyInt(), anyInt()))
                .thenReturn(dishList);
        when(dishResponseMapper.toResponseDtoList(anyList())).thenReturn(dishResponseList);

        //  When
        List<DishResponseDto> dishes = dishHandler.getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size);

        //  Then
        assertEquals(0, dishes.size());

        verify(dishServicePort).getAllDishesByIdRestaurantAndIdCategory(anyInt(), anyInt(), anyInt(), anyInt());
        verify(dishResponseMapper).toResponseDtoList(anyList());
    }

    //######################################
    //              METHODS
    //######################################

    List<Dish> getDishList() {
        Dish dish01 = new Dish(null, "PAPA RELLENA", category, "", 10, restaurant, "", true);
        Dish dish02 = new Dish(null, "LOMO SALTADO", category, "", 20, restaurant, "", true);
        Dish dish03 = new Dish(null, "ARROZ CON POLLO", category, "", 30, restaurant, "", true);

        return List.of(dish01, dish02, dish03);
    }

    List<DishResponseDto> getDishResponseList() {
        DishResponseDto dish01 = new DishResponseDto("PAPA RELLENA", category.getName(), "", 10, restaurant.getName(), "", true);
        DishResponseDto dish02 = new DishResponseDto( "LOMO SALTADO", category.getName(), "", 20, restaurant.getName(), "", true);
        DishResponseDto dish03 = new DishResponseDto( "ARROZ CON POLLO", category.getName(), "", 30, restaurant.getName(), "", true);

        return List.of(dish01, dish02, dish03);
    }
}
