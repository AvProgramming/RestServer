package com.example.practica6rest.service;

import com.example.practica6rest.model.Restaurant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantService {
    Restaurant getById(Long id);

    ResponseEntity<Restaurant> registry(Restaurant newRestaurant);

    List<Restaurant> getAll();

    Restaurant update(Restaurant newRestaurant, Long id);

    void delete(Long id);
}
