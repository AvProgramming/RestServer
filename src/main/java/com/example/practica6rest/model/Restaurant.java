package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    private String city;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "restaurant")
    private List<RestDesk> restDesks;

    public Restaurant(String city, Integer capacity) {
        this.city = city;
        this.capacity = capacity;
    }

    public Restaurant() {
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
