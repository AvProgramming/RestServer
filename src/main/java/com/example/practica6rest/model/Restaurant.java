package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Restaurant  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "restaurant")
    private Collection<FoodOrder> foodOrder;

    public Restaurant(String name, Integer capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Restaurant() {
    }

    public Collection<FoodOrder> getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(Collection<FoodOrder> foodOrder) {
        this.foodOrder = foodOrder;
    }
}
