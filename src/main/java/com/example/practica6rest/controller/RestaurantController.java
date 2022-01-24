package com.example.practica6rest.controller;

import com.example.practica6rest.model.Restaurant;
import com.example.practica6rest.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping(value = "/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant newRestaurant) {
        return restaurantService.registry(newRestaurant);
    }

    @GetMapping(value = "/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getById(id);
    }

    @PutMapping(value = "/restaurants/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id)  {
        return restaurantService.update(newRestaurant, id);
    }

    @DeleteMapping(value = "/restaurants/{id}")
    public void deleteRestaurant(@PathVariable Long id)  {
        restaurantService.delete(id);
    }
}
