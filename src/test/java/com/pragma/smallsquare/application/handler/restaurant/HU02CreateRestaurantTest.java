package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.request.RestaurantRequest;
import com.pragma.smallsquare.application.dto.response.UserResponse;
import com.pragma.smallsquare.application.mapper.IRestaurantRequestMapper;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.application.exceptions.UserIsNoOwnerException;
import com.pragma.smallsquare.insfrastructure.output.feign.IUserFeignClient;
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
class HU02CreateRestaurantTest {

    @InjectMocks
    private RestaurantHandler restaurantHandler;

    @Mock
    private IRestaurantServicePort restaurantServicePort;
    @Mock
    private IRestaurantRequestMapper restaurantRequestMapper;
    @Mock
    private IUserFeignClient userFeignClient;

    private Validator validator;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        restaurant = new Restaurant();
        restaurant.setId(2);
        restaurant.setName("RESTAURANT 123");
        restaurant.setAddress("1111111110111");
        restaurant.setPhone("+51999888777");
        restaurant.setUrlLogo("https://images.pexels.com/photos/restaurant.jpg");
        restaurant.setIdOwner(2);
    }

    @Test
    @DisplayName("Create a Restaurant with CORRECT OWNER User")
    void createRestaurant_WithCorrectOwnerUser() {
        //  Given
        when(restaurantRequestMapper.toRestaurant(any(RestaurantRequest.class))).thenReturn(restaurant);
        when(userFeignClient.getUserById(anyInt())).thenReturn(getUserNoOwnerResponse());

        //  When
        //  Then
        assertThrows(UserIsNoOwnerException.class, () -> restaurantHandler.saveRestaurantDto(getRestaurantRequest_WithValidValues()));
    }

    @Test
    @DisplayName("Create a Restaurant with INCORRECT OWNER User")
    void createRestaurant_WithIncorrectOwnerUser() {
        //  Given
        when(restaurantRequestMapper.toRestaurant(any(RestaurantRequest.class))).thenReturn(restaurant);
        when(userFeignClient.getUserById(anyInt())).thenReturn(getUserOwnerResponse());

        //  When
        restaurantHandler.saveRestaurantDto(getRestaurantRequest_WithValidValues());

        //  Then
        verify(restaurantRequestMapper).toRestaurant(any(RestaurantRequest.class));
        verify(userFeignClient).getUserById(anyInt());
        verify(restaurantServicePort).saveRestaurant(any(Restaurant.class));
    }

    @Test
    @DisplayName("Create a Restaurant with RIGHT REQUEST")
    void createRestaurant_WithRightRequest() {
        //  Given
        RestaurantRequest restaurantRequest = getRestaurantRequest_WithValidValues();

        //  When
        Set<ConstraintViolation<RestaurantRequest>> violations = validator.validate(restaurantRequest);

        //  Then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Create a Restaurant with BAD REQUEST")
    void createRestaurant_WithBadRequest() {
        //  Given
        RestaurantRequest restaurantRequest = getRestaurantRequest_WithInvalidValues();

        //  When
        Set<ConstraintViolation<RestaurantRequest>> violations = validator.validate(restaurantRequest);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        //  Then
        assertEquals(6, violations.size());
        assertTrue(messages.contains("RESTAURANT NAME must have one letter at least"));
        assertTrue(messages.contains("NIT must be only numbers"));
        assertTrue(messages.contains("RESTAURANT ADDRESS field is required"));
        assertTrue(messages.contains("RESTAURANT PHONE field is required"));
        assertTrue(messages.contains("RESTAURANT URL LOGO field is required"));
        assertTrue(messages.contains("RESTAURANT OWNER field is required"));
    }


    //######################################
    //              METHODS
    //######################################
    RestaurantRequest getRestaurantRequest_WithValidValues() {
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        restaurantRequest.setName("RESTAURANT 123");
        restaurantRequest.setNit("1111111110111");
        restaurantRequest.setAddress("AV. ARENALES 1000");
        restaurantRequest.setPhone("+51999888777");
        restaurantRequest.setUrlLogo("https://images.pexels.com/photos/restaurant.jpg");
        restaurantRequest.setIdOwner(2);

        return restaurantRequest;
    }

    RestaurantRequest getRestaurantRequest_WithInvalidValues() {
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        restaurantRequest.setName("$$$###%%%");
        restaurantRequest.setNit("123456789a");
        restaurantRequest.setAddress("");
        restaurantRequest.setPhone("");
        restaurantRequest.setUrlLogo("");
        restaurantRequest.setIdOwner(null);

        return restaurantRequest;
    }

    UserResponse getUserOwnerResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(3);
        userResponse.setName("Oscar");
        userResponse.setSurname("Ramirez");
        userResponse.setDocumentNumber("77777777");
        userResponse.setPhone("+51777999888");
        userResponse.setBirthdate("2000-10-10");
        userResponse.setEmail("oscar@pragma.com.co");
        userResponse.setPassword("$2a$10$LM2MAkzdUYcUB79ebi0Aw.oX5PkfXfZxYVVCE.aEkj0H4ZopwkmFi");
        userResponse.setIdRole(2);
        userResponse.setNameRole("PROPIETARIO");

        return userResponse;
    }

    UserResponse getUserNoOwnerResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(3);
        userResponse.setName("Oscar");
        userResponse.setSurname("Ramirez");
        userResponse.setDocumentNumber("77777777");
        userResponse.setPhone("+51777999888");
        userResponse.setBirthdate("2000-10-10");
        userResponse.setEmail("oscar@pragma.com.co");
        userResponse.setPassword("$2a$10$LM2MAkzdUYcUB79ebi0Aw.oX5PkfXfZxYVVCE.aEkj0H4ZopwkmFi");
        userResponse.setIdRole(3);
        userResponse.setNameRole("EMPLEADO");

        return userResponse;
    }

}