package com.pragma.smallsquare.insfrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;
    Integer price;
    String urlImage;
    Boolean active;

    @ManyToOne
    @JoinColumn(name = "id_category")
    CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    RestaurantEntity restaurant;
}
