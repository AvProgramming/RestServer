package com.example.practica6rest.service;

import com.example.practica6rest.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Product> registry(Product newProduct);

    List<Product> getProduct();

    Product update(Product newProduct, Long id);

    void delete(Long id);
}
