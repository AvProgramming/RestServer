package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.*;
import com.example.practica6rest.repository.*;
import com.example.practica6rest.service.PurchaseService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        Client client = newPurchase.getClient();
        Restaurant restaurant = newPurchase.getRestaurant();

        if (client == null || !clientRepository.existsById(client.getId())) {
            throw new EntityNotFoundException("NO Client");
        }

        if (restaurant== null || !restaurantRepository.existsById(restaurant.getId())) {
            throw new EntityNotFoundException("NO Restaurant");
        }
        String content = newPurchase.getContent();
        newPurchase.setContent("TEMPORAL"); //@TODO

        Purchase purchase = purchaseRepository.save(newPurchase);
        log.info(String.valueOf(purchase));

        Gson gson = new Gson();

        log.info("CONTENT " + newPurchase.getContent());

        List<Product> listOfId = gson.fromJson(content, new TypeToken<List<Product>>() {
        }.getType());
        List<ProductPurchase> productPurchases = new ArrayList<>();

        for (Product product : listOfId) {
//            Product product = productRepository.getById(Long.valueOf(id));
            ProductPurchase productPurchase = new ProductPurchase(purchase, product);
            productPurchases.add(productPurchase);
        }

        productPurchaseRepository.saveAll(productPurchases);

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
            throw new EntityNotFoundException("NO SUCH PURCHASE");     //Todo Exceptions
        }
        purchaseRepository.deleteById(id);
    }
}
