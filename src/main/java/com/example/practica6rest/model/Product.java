package com.example.practica6rest.model;

import com.example.practica6rest.model.enumeral.ProductType;
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
    private String product_name;

    @Column
    private Integer price;

    @Column
    private String img_url;

    @Column
    private Boolean vegan;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private ProductType type;

    @OneToMany(mappedBy = "product")
    private List<ProductPurchase> productPurchase;

    public Product(String product_name, Integer price, String img_url, Boolean vegan, ProductType type) {
        this.product_name = product_name;
        this.price = price;
        this.img_url = img_url;
        this.vegan = vegan;
        this.type = type;
    }

    public Product() {
    }
}
