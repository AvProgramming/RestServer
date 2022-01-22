package com.example.practica6rest.repository;

import com.example.practica6rest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
