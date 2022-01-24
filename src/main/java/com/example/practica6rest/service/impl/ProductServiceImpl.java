package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Product;
import com.example.practica6rest.repository.ProductRepository;
import com.example.practica6rest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Product> registry(Product newProduct) {
        //TODO logic
        Product product = productRepository.save(newProduct);
        log.info(String.valueOf(product));

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Override
    public List<Product> getAll() {
        //TODO logic
        return productRepository.findAll();
    }

    @Override
    public Product update(Product newProduct, Long id) {
        //TODO logic
        return productRepository.findById(id)
                .map(product -> {
                    product.setProduct_name(newProduct.getProduct_name());
                    product.setPrice(newProduct.getPrice());
                    product.setImg_url(newProduct.getImg_url());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH DESK"));
    }

    @Override
    public void delete(Long id) {
        //TODO logic
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH PRODUCT");     //@Todo Exceptions
        }
        productRepository.deleteById(id);
    }
}
