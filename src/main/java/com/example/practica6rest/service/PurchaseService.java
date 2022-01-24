package com.example.practica6rest.service;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.model.Purchase;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseService {
    Purchase getById(Long id);

    ResponseEntity<Purchase> registry(Purchase newPurchase);

    List<Purchase> getAll();

    Purchase update(Purchase newPurchase, Long id);

    void delete(Long id);
}
