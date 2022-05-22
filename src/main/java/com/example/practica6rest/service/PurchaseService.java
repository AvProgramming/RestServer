package com.example.practica6rest.service;

import com.example.practica6rest.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PurchaseService {
    Purchase getById(Long id);

    ResponseEntity<Purchase> registry(Purchase newPurchase);

    List<Purchase> getAll();

    Purchase update(Purchase newPurchase, Long id);

    void delete(Long id);

    Page<Purchase> findPaginated(int page, int size);

    Page<Purchase> findPaginated(int page, int size, String field);

    ResponseEntity<Map<String, Object>>findPaginatedFiltered(int page, int size, Integer id);
}
