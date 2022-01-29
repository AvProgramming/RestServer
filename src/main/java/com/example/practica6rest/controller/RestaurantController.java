package com.example.practica6rest.controller;

import com.example.practica6rest.model.Restaurant;
import com.example.practica6rest.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping(value = "/")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant newRestaurant) {
        return restaurantService.registry(newRestaurant);
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id) {
        return restaurantService.update(newRestaurant, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.delete(id);
    }
}
