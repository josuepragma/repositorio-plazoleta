package com.pragma.smallsquare.insfrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Date orderDate;
    String status;
    Integer idCustomer;
    Integer idChef;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    RestaurantEntity restaurant;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDishEntity> ordersDishes;

    public OrderEntity() {
        ordersDishes = new ArrayList<>();
    }
}
