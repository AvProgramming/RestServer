package com.example.practica6rest.model;

import com.example.practica6rest.model.MtoM.ProductPurchaseId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference("purchase_productPurchases")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    @JsonBackReference("product_productPurchases")
    private Product product;

    public ProductPurchase(Purchase purchase, Product product) {
        this.purchase = purchase;
        this.product = product;
    }

    public ProductPurchase() {
    }
}
