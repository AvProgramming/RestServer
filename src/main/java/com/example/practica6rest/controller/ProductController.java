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
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<Product> createClient(@RequestBody Product newProduct) {
        return productService.registry(newProduct);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public Product updateClient(@RequestBody Product product, @PathVariable Long id) {
        return productService.update(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        productService.delete(id);
    }
}
