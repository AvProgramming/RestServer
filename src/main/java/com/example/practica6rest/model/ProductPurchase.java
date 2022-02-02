package com.example.practica6rest.model;

import com.example.practica6rest.model.MtoM.ProductPurchaseId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private ProductPurchaseId id = new ProductPurchaseId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("purchase_id")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
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
