package com.pragma.smallsquare.domain.model;

public class OrderDish {

    private Integer id;
    private Integer idOrder;
    private Integer idDish;
    private Integer quantity;

    public OrderDish() {
    }

    public OrderDish(Integer id, Integer idOrder, Integer idDish, Integer quantity) {
        this.id = id;
        this.idOrder = idOrder;
        this.idDish = idDish;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdDish() {
        return idDish;
    }

    public void setIdDish(Integer idDish) {
        this.idDish = idDish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
