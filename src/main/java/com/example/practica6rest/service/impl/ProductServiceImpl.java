package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Product;
import com.example.practica6rest.repository.ProductRepository;
import com.example.practica6rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Product> registry(Product newProduct) {
        Product product = productRepository.save(newProduct);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product newProduct, Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setProduct_name(newProduct.getProduct_name());
                    product.setPrice(newProduct.getPrice());
                    product.setImg_url(newProduct.getImg_url());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
