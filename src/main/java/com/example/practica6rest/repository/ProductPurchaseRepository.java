package com.example.practica6rest.repository;

import com.example.practica6rest.model.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {
}
