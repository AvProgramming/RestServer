package com.example.practica6rest.controller;

import com.example.practica6rest.model.Product;
import com.example.practica6rest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProduct();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createClient(@RequestBody Product newProduct) {
        return productService.registry(newProduct);
    }

    @PutMapping("/products/{id}")
    public Product updateClient(@RequestBody Product product, @PathVariable Long id) {
        return productService.update(product, id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteClient(@PathVariable Long id) {
        productService.delete(id);
    }
}
