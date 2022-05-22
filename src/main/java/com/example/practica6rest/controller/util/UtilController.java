package com.example.practica6rest.controller.util;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.model.Product;
import com.example.practica6rest.model.Restaurant;
import com.example.practica6rest.repository.DeskRepository;
import com.example.practica6rest.repository.ProductRepository;
import com.example.practica6rest.repository.RestaurantRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
@RestController
public class UtilController {

    private final DeskRepository deskRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UtilController(DeskRepository deskRepository, RestaurantRepository restaurantRepository, ProductRepository productRepository) {
        this.deskRepository = deskRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("/json")
    public void json() {
        //get json data.sql from resources
        URL urlDesks = this.getClass().getClassLoader().getResource("desks.json");
        URL urlRestaurants = this.getClass().getClassLoader().getResource("restaurants.json");
        URL urlProducts = this.getClass().getClassLoader().getResource("products.json");

        if (urlDesks != null && urlRestaurants != null && urlProducts != null) {
            File jsonDesk = new File(urlDesks.getFile());
            File jsonRest = new File(urlRestaurants.getFile());
            File jsonProd = new File(urlProducts.getFile());

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<Desk> desks = objectMapper.readValue(jsonDesk, new TypeReference<>() {
                });
                List<Restaurant> rests = objectMapper.readValue(jsonRest, new TypeReference<>() {
                });
                List<Product> products = objectMapper.readValue(jsonProd, new TypeReference<>() {
                });

                deskRepository.saveAll(desks);
                restaurantRepository.saveAll(rests);
                productRepository.saveAll(products);

                log.info("All records saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("url is null");
        }

    }

}
