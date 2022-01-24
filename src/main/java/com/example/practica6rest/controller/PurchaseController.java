package com.example.practica6rest.controller;

import com.example.practica6rest.model.Purchase;
import com.example.practica6rest.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase")
    public List<Purchase> getPurchases() {
        return purchaseService.getAll();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase newPurchase) {
        return purchaseService.registry(newPurchase);
    }


    @GetMapping(value = "/purchase/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        return purchaseService.getById(id);
    }

    @PutMapping("/purchase/{id}")
    public Purchase updatePurchase(@RequestBody Purchase purchase, @PathVariable Long id) {
        return purchaseService.update(purchase, id);
    }

    @DeleteMapping("/purchase/{id}")
    public void deletePurchase(@PathVariable Long id) {
        purchaseService.delete(id);
    }
}
