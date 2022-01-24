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
    private List<Purchase> purchase;

    @OneToMany(mappedBy = "restaurant")
    private List<RestDesk> restDesks;

    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservations;

    public Restaurant(String city, Integer capacity) {
        this.city = city;
        this.capacity = capacity;
    }

    public Restaurant() {
    }
}
