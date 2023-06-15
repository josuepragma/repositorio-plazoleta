package com.pragma.smallsquare.domain.model;

import java.util.Date;

public class Order {
    Integer id;
    Date orderDate;
    String status;
    Integer idCustomer;
    Integer idChef;
    Restaurant restaurant;

    public Order() {
    }

    public Order(Integer id, Date orderDate, String status, Integer idCustomer, Integer idChef, Restaurant restaurant) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.idCustomer = idCustomer;
        this.idChef = idChef;
        this.restaurant = restaurant;
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
}
