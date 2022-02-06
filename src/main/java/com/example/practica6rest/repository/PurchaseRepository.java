package com.example.practica6rest.repository;

import com.example.practica6rest.model.Purchase;
import com.example.practica6rest.model.enumeral.PurchaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Page<Purchase> findByType(PurchaseStatus type, Pageable pageable);
}
