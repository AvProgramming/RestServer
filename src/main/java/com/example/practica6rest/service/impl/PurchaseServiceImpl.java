package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.*;
import com.example.practica6rest.model.enumeral.PurchaseStatus;
import com.example.practica6rest.repository.*;
import com.example.practica6rest.service.PurchaseService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ClientRepository clientRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductPurchaseRepository productPurchaseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ClientRepository clientRepository, RestaurantRepository restaurantRepository, ProductPurchaseRepository productPurchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.clientRepository = clientRepository;
        this.restaurantRepository = restaurantRepository;
        this.productPurchaseRepository = productPurchaseRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Purchase getById(Long id) {
        if (!purchaseRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH Purchase");
        }
        return purchaseRepository.findById(id).get();
    }

    @Transactional
    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Purchase> registry(Purchase newPurchase) {
        log.info("CONTENT of purchase " + newPurchase);

        Client client = newPurchase.getClient();
        Restaurant restaurant = newPurchase.getRestaurant();

        if (client == null || !clientRepository.existsById(client.getId())) {
            throw new EntityNotFoundException("NO Client");
        }

        if (restaurant == null || !restaurantRepository.existsById(restaurant.getId())) {
            throw new EntityNotFoundException("NO Restaurant");
        }

        Purchase purchase = purchaseRepository.save(newPurchase);
        log.info("Purchase successfully added  + " + purchase);

        String content = newPurchase.getContent();

        Gson gson = new Gson();

        List<Integer> listOfId = gson.fromJson(content, new TypeToken<List<Integer>>() {
        }.getType());
        List<ProductPurchase> productPurchases = new ArrayList<>();

        Map<Integer, Integer> mapOfhQuantity = new HashMap<>();

        for (Integer keyId : listOfId) {
            if (mapOfhQuantity.containsKey(keyId)) {
                mapOfhQuantity.put(keyId, mapOfhQuantity.get(keyId) + 1);
            } else {
                mapOfhQuantity.put(keyId, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : mapOfhQuantity.entrySet()) {
            Product product = productRepository.getById(Long.valueOf(entry.getKey()));
            ProductPurchase productPurchase = new ProductPurchase(purchase, product, entry.getValue());
            productPurchases.add(productPurchase);
        }

        productPurchaseRepository.saveAll(productPurchases);
//        log.info("Product Purchases successfully added  + " + productPurchases);

        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase update(Purchase newPurchase, Long id) {
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    purchase.setType(newPurchase.getType());
                    return purchaseRepository.save(purchase);
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH PURCHASE"));
    }

    @Override
    public void delete(Long id) {
        if (!purchaseRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH PURCHASE");     //Todo Exceptions
        }
        log.info("success");
        purchaseRepository.deleteById(id);
    }

    @Override
    public Page<Purchase> findPaginated(int page, int size) {
        return purchaseRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Purchase> findPaginated(int page, int size, String field) {
        return purchaseRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(field)));
    }

    @Override
    public ResponseEntity<Map<String, Object>> findPaginatedFiltered(int page, int size, String field) {
        try {
            List<Purchase> purchases;
            Pageable paging = PageRequest.of(page, size);

            Page<Purchase> pagePurchases;
            if (field == null) {
                pagePurchases = purchaseRepository.findAll(paging);
            } else {
                pagePurchases = purchaseRepository.findByType(PurchaseStatus.valueOf(field), paging);
            }
            purchases = pagePurchases.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("purchases", purchases);
            response.put("currentPage", pagePurchases.getNumber());
            response.put("totalItems", pagePurchases.getTotalElements());
            response.put("totalPages", pagePurchases.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
