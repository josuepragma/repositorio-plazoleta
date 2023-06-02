package com.pragma.smallsquare.restaurant.domain.model;

import java.math.BigDecimal;

public class Dish {
    Integer id;
    String name;
    Integer idCategory;
    String description;
    BigDecimal price;
    Integer idRestaurant;
    String urlImage;
    Boolean isActive;
}
