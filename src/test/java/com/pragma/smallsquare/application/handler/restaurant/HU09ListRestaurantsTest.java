package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.response.RestaurantSortResponse;
import com.pragma.smallsquare.application.mapper.IRestaurantSortResponseMapper;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HU09ListRestaurantsTest {

    @InjectMocks
    private RestaurantHandler restaurantHandler;

    @Mock
    private IRestaurantServicePort restaurantServicePort;
    @Mock
    private IRestaurantSortResponseMapper restaurantSortResponseMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("List all Restaurants Order By Name Asc")
    void getAllRestaurants_OrderByNameAsc() {
        int page = 1;
        int size = 5;
        //  Given
        when(restaurantServicePort.getAllRestaurantsOrderByName(page, size)).thenReturn(getRestaurantList02());
        when(restaurantSortResponseMapper.toResponseList(any())).thenReturn(getRestaurantSortResponseDtoList02());

        //  When
        List<RestaurantSortResponse> restaurantSort = restaurantHandler.getAllRestaurantsDtoOrderByName(page, size);

        //  Then
        assertEquals(3, restaurantSort.size());
        assertEquals("https://images.pexels8.com", restaurantSort.get(2).getUrlLogo());
        assertEquals("RESTAURANT06", restaurantSort.get(0).getName());
        verify(restaurantServicePort).getAllRestaurantsOrderByName(anyInt(), anyInt());
        verify(restaurantSortResponseMapper).toResponseList(anyList());
    }

    @Test
    @DisplayName("List Empty List of Restaurants")
    void getEmptyRestaurants_OrderByNameAsc() {
        List<Restaurant> restaurants = Collections.emptyList();
        List<RestaurantSortResponse> restaurantSortResponse = Collections.emptyList();
        int page = 1;
        int size = 5;

        //  Given
        when(restaurantServicePort.getAllRestaurantsOrderByName(anyInt(), anyInt())).thenReturn(restaurants);
        when(restaurantSortResponseMapper.toResponseList(any())).thenReturn(restaurantSortResponse);

        //  When
        List<RestaurantSortResponse> restaurantSort = restaurantHandler.getAllRestaurantsDtoOrderByName(page, size);

        //  Then
        assertEquals(0, restaurantSort.size());
        verify(restaurantServicePort).getAllRestaurantsOrderByName(anyInt(), anyInt());
        verify(restaurantSortResponseMapper).toResponseList(anyList());

    }

    //######################################
    //              METHODS
    //######################################
    List<Restaurant> getRestaurantList01() {
        Restaurant r01 = new Restaurant(1, "RESTAURANT01", "", "", "https://images.pexels1.com", "", 17);
        Restaurant r02 = new Restaurant(2, "RESTAURANT02", "", "", "https://images.pexels2.com", "", 17);
        Restaurant r03 = new Restaurant(3, "RESTAURANT03", "", "", "https://images.pexels3.com", "", 17);
        Restaurant r04 = new Restaurant(4, "RESTAURANT04", "", "", "https://images.pexels4.com", "", 17);
        Restaurant r05 = new Restaurant(5, "RESTAURANT05", "", "", "https://images.pexels5.com", "", 17);

        return List.of(r01, r02, r03, r04, r05);
    }

    List<Restaurant> getRestaurantList02() {
        Restaurant r06 = new Restaurant(6, "RESTAURANT06", "", "", "https://images.pexels6.com", "", 17);
        Restaurant r07 = new Restaurant(7, "RESTAURANT07", "", "", "https://images.pexels7.com", "", 17);
        Restaurant r08 = new Restaurant(8, "RESTAURANT08", "", "", "https://images.pexels8.com", "", 17);

        return List.of(r06, r07, r08);
    }

    List<RestaurantSortResponse> getRestaurantSortResponseDtoList01() {
        RestaurantSortResponse r01 = new RestaurantSortResponse("RESTAURANT01", "https://images.pexels1.com");
        RestaurantSortResponse r02 = new RestaurantSortResponse("RESTAURANT02", "https://images.pexels2.com");
        RestaurantSortResponse r03 = new RestaurantSortResponse("RESTAURANT03", "https://images.pexels3.com");
        RestaurantSortResponse r04 = new RestaurantSortResponse("RESTAURANT04", "https://images.pexels4.com");
        RestaurantSortResponse r05 = new RestaurantSortResponse("RESTAURANT05", "https://images.pexels5.com");

        return List.of(r01, r02, r03, r04, r05);
    }

    List<RestaurantSortResponse> getRestaurantSortResponseDtoList02() {
        RestaurantSortResponse r06 = new RestaurantSortResponse("RESTAURANT06", "https://images.pexels6.com");
        RestaurantSortResponse r07 = new RestaurantSortResponse("RESTAURANT07", "https://images.pexels7.com");
        RestaurantSortResponse r08 = new RestaurantSortResponse("RESTAURANT08", "https://images.pexels8.com");

        return List.of(r06, r07, r08);
    }

}
