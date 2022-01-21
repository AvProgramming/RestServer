package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.Collection;
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

    @OneToMany(mappedBy = "product")
    private List<ProductPurchase> productPurchase;

    public Product(String product_name, Integer price, String img_url) {
        this.product_name = product_name;
        this.price = price;
        this.img_url = img_url;
    }

    public Product() {
    }
}
