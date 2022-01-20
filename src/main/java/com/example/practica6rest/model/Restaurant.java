package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    private String city;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodOrder> foodOrder;

    @OneToMany(mappedBy = "restaurant")
    private List<RestDesk> restDesks;

    public Restaurant(String city, Integer capacity) {
        this.city = city;
        this.capacity = capacity;
    }

    public Restaurant() {
    }

    public List<FoodOrder> getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(List<FoodOrder> foodOrder) {
        this.foodOrder = foodOrder;
    }
}
