package com.example.practica6rest.controller;

import com.example.practica6rest.model.Purchase;
import com.example.practica6rest.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public List<Purchase> getPurchases() {
        return purchaseService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase newPurchase) {
        return purchaseService.registry(newPurchase);
    }

    @GetMapping(value = "/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        return purchaseService.getById(id);
    }

    @PatchMapping("/{id}")
    public Purchase updatePurchase(@RequestBody Purchase purchase, @PathVariable Long id) {
        return purchaseService.update(purchase, id);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Long id) {
        purchaseService.delete(id);
    }

//    @GetMapping(value = "/pagination/{page}/{size}")
//    public Page<Purchase> getPurchasePageable(@PathVariable int page, @PathVariable int size) {
//        return purchaseService.findPaginated(page, size); //@TODO ResponseEntity
//    }

    @GetMapping(value = "/pagination/{page}/{size}/{field}")
    public Page<Purchase> getPurchasePageableAndSorted(@PathVariable int page, @PathVariable int size, @PathVariable String field) {
        return purchaseService.findPaginated(page, size, field); //@TODO ResponseEntity
    }

    @GetMapping(value = "/pagination/{page}/{size}")
    public ResponseEntity<Map<String, Object>> getPurchasePageableAndFiltered(@PathVariable int page, @PathVariable int size, @RequestParam(required = false) String field) {
        return purchaseService.findPaginatedFiltered(page, size, field); //@TODO ResponseEntity
    }
}
