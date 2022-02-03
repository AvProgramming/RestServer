package com.example.practica6rest.model;

import com.example.practica6rest.model.enumeral.ProductType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String img_url;

    @Column
    private Boolean vegan;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private ProductType type;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference("product_productPurchases")
    private List<ProductPurchase> productPurchase;

    public Product(String name, Double price, String img_url, Boolean vegan, ProductType type) {
        this.name = name;
        this.price = price;
        this.img_url = img_url;
        this.vegan = vegan;
        this.type = type;
    }

    public Product() {
    }
}
