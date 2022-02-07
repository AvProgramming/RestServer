package com.example.practica6rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("restaurant_purchases")
    private List<Purchase> purchase;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("restaurant_restDesks")
    private List<RestDesk> restDesks;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("restaurant_reservations")
    private List<Reservation> reservations;

    public Restaurant(String city, Integer capacity) {
        this.city = city;
        this.capacity = capacity;
    }

    public Restaurant() {
    }
}
