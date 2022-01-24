package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Purchase;
import com.example.practica6rest.repository.PurchaseRepository;
import com.example.practica6rest.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public ResponseEntity<Purchase> registry(Purchase newPurchase) {
        //TODO logic
        Purchase purchase = purchaseRepository.save(newPurchase);
        log.info(String.valueOf(purchase));

        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    @Override
    public List<Purchase> getAll() {
        //TODO logic
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase update(Purchase newPurchase, Long id) {
        //TODO logic
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    purchase.setTime(newPurchase.getTime());
                    purchase.setContent(newPurchase.getContent());
                    purchase.setRestaurant(newPurchase.getRestaurant());
                    purchase.setClient(newPurchase.getClient());
                    return purchaseRepository.save(purchase);
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH PURCHASE"));
    }

    @Override
    public void delete(Long id) {
    //TODO logic
        if (!purchaseRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH PURCHASE");     //@Todo Exceptions
        }
        purchaseRepository.deleteById(id);
    }
}
