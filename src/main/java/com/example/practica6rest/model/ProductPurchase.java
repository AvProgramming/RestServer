package com.example.practica6rest.model;

import com.example.practica6rest.model.MtoM.ProductPurchaseId;
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
    private ProductPurchaseId id;

    @ManyToOne
    @MapsId("purchase_id")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Desk desk;

    public ProductPurchase(Purchase purchase, Desk desk) {
        this.purchase = purchase;
        this.desk = desk;
    }

    public ProductPurchase() {
    }
}
