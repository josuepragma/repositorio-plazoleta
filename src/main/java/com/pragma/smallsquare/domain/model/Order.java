package com.pragma.smallsquare.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    Integer id;
    Date orderDate;
    String status;
    Integer idCustomer;
    Integer idChef;
    Restaurant restaurant;
    List<OrderDish> ordersDishes;

    public Order() {
        ordersDishes = new ArrayList<>();
    }

    public Order(Integer id, Date orderDate, String status, Integer idCustomer, Integer idChef, Restaurant restaurant, List<OrderDish> ordersDishes) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.idCustomer = idCustomer;
        this.idChef = idChef;
        this.restaurant = restaurant;
        this.ordersDishes = ordersDishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdChef() {
        return idChef;
    }

    public void setIdChef(Integer idChef) {
        this.idChef = idChef;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderDish> getOrdersDishes() {
        return ordersDishes;
    }

    public void setOrdersDishes(List<OrderDish> ordersDishes) {
        this.ordersDishes = ordersDishes;
    }

}
