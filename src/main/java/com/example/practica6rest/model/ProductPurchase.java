package com.example.practica6rest.model;

import com.example.practica6rest.model.MtoM.ProductOrderId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_purchase")
public class ProductPurchase {

    @EmbeddedId
    private ProductOrderId id;

    @ManyToOne
    @MapsId("purchase_id")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductPurchase(Purchase purchase, Product product) {
        this.purchase = purchase;
        this.product = product;
    }

    public ProductPurchase() {
    }
}
