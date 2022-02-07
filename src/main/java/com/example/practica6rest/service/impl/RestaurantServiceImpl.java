package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Restaurant;
import com.example.practica6rest.repository.RestaurantRepository;
import com.example.practica6rest.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant getById(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");

        }
        log.info("Restaurant with id: " + id + " retrieved successfully");
        return restaurantRepository.getById(id);
    }

    @Override
    public ResponseEntity<Restaurant> registry(Restaurant newRestaurant) {
        //TODO logic
        Restaurant restaurant = restaurantRepository.save(newRestaurant);
        log.info(String.valueOf(restaurant));

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant update(Restaurant newRestaurant, Long id) {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setCity(newRestaurant.getCity());
                    restaurant.setCapacity(newRestaurant.getCapacity());
                    log.info("Restaurant with id: " + id + "is updated successfully");
                    return restaurantRepository.save(restaurant);
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH RESTAURANT"));
    }

    @Override
    public void delete(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH PURCHASE");
        }
        log.info("Restaurant with id: " + id + " is successfully deleted");
        restaurantRepository.deleteById(id);
    }
}
