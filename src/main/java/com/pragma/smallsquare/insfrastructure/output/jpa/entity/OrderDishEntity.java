package com.pragma.smallsquare.insfrastructure.output.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    OrderEntity order;

//    @ManyToOne
    @JoinColumn(name = "id_dish")
    Integer idDish;

    Integer quantity;
}
