package com.example.practica6rest;

import com.example.practica6rest.model.Product;
import com.example.practica6rest.model.enumeral.ProductType;
import com.example.practica6rest.service.ClientService;
import com.example.practica6rest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Practice6RestApplication {

    private final ProductService productService;
    private final ClientService clientService;

    @Autowired
    public Practice6RestApplication(ProductService productService, ClientService clientService) {
        this.productService = productService;
        this.clientService = clientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Practice6RestApplication.class, args);
    }

    @PostConstruct
    private void insertProducts() {

        Product pizza = new Product("Pizza", 10.5, "https://static.sscontent.com/prodimg/thumb/500/500/products/124/v1061874_prozis_4-x-ultra-thin-pizza" +
                "-crusts_4.jpg", false, ProductType.ENTRY);
        Product ramen = new Product("Ramen", 11.0, "https://www.elmundoeats.com/wp-content/uploads/2021/02/FP-Quick-30-minutes-chicken-ramen-500x500.jpg",
                false, ProductType.RAMEN);
        Product sushi = new Product("Sushi", 14.5, "https://blog.dia.es/wp-content/uploads/2020/06/sushi-8V3FQM6-500x500.jpg", true, ProductType.SUSHI);
        List<Product> productsToInsert = new ArrayList<>(List.of(pizza, ramen, sushi));
        List<Product> productsFromDb = productService.getAll();

        ArrayList<Product> listOfEqualProducts = new ArrayList<>();

        try {
            productsToInsert.forEach(product ->
                    productsFromDb.stream()
                            .filter(product1 -> product.getImg_url().equals(product1.getImg_url()))
                            .forEach(listOfEqualProducts::add));
        } catch (NullPointerException e) {
            log.info("there is no info yet");
        }

        if (listOfEqualProducts.isEmpty()) {
            for (Product product : productsToInsert) {
                productService.registry(product);
            }
        }
    }
}
