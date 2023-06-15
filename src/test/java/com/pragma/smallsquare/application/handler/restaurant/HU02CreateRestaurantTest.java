package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.application.dto.response.UserResponseDto;
import com.pragma.smallsquare.application.mapper.IRestaurantRequestMapper;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.insfrastructure.exceptions.UserIsNoOwnerException;
import com.pragma.smallsquare.insfrastructure.feign.client.IUserFeignClient;
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
        when(restaurantRequestMapper.toRestaurant(any(RestaurantRequestDto.class))).thenReturn(restaurant);
        when(userFeignClient.getUserById(anyInt())).thenReturn(getUserNoOwnerResponse());

        //  When
        //  Then
        assertThrows(UserIsNoOwnerException.class, () -> restaurantHandler.saveRestaurantDto(getRestaurantRequest_WithValidValues()));
    }

    @Test
    @DisplayName("Create a Restaurant with INCORRECT OWNER User")
    void createRestaurant_WithIncorrectOwnerUser() {
        //  Given
        when(restaurantRequestMapper.toRestaurant(any(RestaurantRequestDto.class))).thenReturn(restaurant);
        when(userFeignClient.getUserById(anyInt())).thenReturn(getUserOwnerResponse());

        //  When
        restaurantHandler.saveRestaurantDto(getRestaurantRequest_WithValidValues());

        //  Then
        verify(restaurantRequestMapper).toRestaurant(any(RestaurantRequestDto.class));
        verify(userFeignClient).getUserById(anyInt());
        verify(restaurantServicePort).saveRestaurant(any(Restaurant.class));
    }

    @Test
    @DisplayName("Create a Restaurant with RIGHT REQUEST")
    void createRestaurant_WithRightRequest() {
        //  Given
        RestaurantRequestDto restaurantRequest = getRestaurantRequest_WithValidValues();

        //  When
        Set<ConstraintViolation<RestaurantRequestDto>> violations = validator.validate(restaurantRequest);

        //  Then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Create a Restaurant with BAD REQUEST")
    void createRestaurant_WithBadRequest() {
        //  Given
        RestaurantRequestDto restaurantRequest = getRestaurantRequest_WithInvalidValues();

        //  When
        Set<ConstraintViolation<RestaurantRequestDto>> violations = validator.validate(restaurantRequest);
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
    RestaurantRequestDto getRestaurantRequest_WithValidValues() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
        restaurantRequestDto.setName("RESTAURANT 123");
        restaurantRequestDto.setNit("1111111110111");
        restaurantRequestDto.setAddress("AV. ARENALES 1000");
        restaurantRequestDto.setPhone("+51999888777");
        restaurantRequestDto.setUrlLogo("https://images.pexels.com/photos/restaurant.jpg");
        restaurantRequestDto.setIdOwner(2);

        return restaurantRequestDto;
    }

    RestaurantRequestDto getRestaurantRequest_WithInvalidValues() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
        restaurantRequestDto.setName("$$$###%%%");
        restaurantRequestDto.setNit("123456789a");
        restaurantRequestDto.setAddress("");
        restaurantRequestDto.setPhone("");
        restaurantRequestDto.setUrlLogo("");
        restaurantRequestDto.setIdOwner(null);

        return restaurantRequestDto;
    }

    UserResponseDto getUserOwnerResponse() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(3);
        userResponseDto.setName("Oscar");
        userResponseDto.setSurname("Ramirez");
        userResponseDto.setDocumentNumber("77777777");
        userResponseDto.setPhone("+51777999888");
        userResponseDto.setBirthdate("2000-10-10");
        userResponseDto.setEmail("oscar@pragma.com.co");
        userResponseDto.setPassword("$2a$10$LM2MAkzdUYcUB79ebi0Aw.oX5PkfXfZxYVVCE.aEkj0H4ZopwkmFi");
        userResponseDto.setIdRole(2);
        userResponseDto.setNameRole("PROPIETARIO");

        return userResponseDto;
    }

    UserResponseDto getUserNoOwnerResponse() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(3);
        userResponseDto.setName("Oscar");
        userResponseDto.setSurname("Ramirez");
        userResponseDto.setDocumentNumber("77777777");
        userResponseDto.setPhone("+51777999888");
        userResponseDto.setBirthdate("2000-10-10");
        userResponseDto.setEmail("oscar@pragma.com.co");
        userResponseDto.setPassword("$2a$10$LM2MAkzdUYcUB79ebi0Aw.oX5PkfXfZxYVVCE.aEkj0H4ZopwkmFi");
        userResponseDto.setIdRole(3);
        userResponseDto.setNameRole("EMPLEADO");

        return userResponseDto;
    }

}