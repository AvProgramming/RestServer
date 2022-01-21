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
@Table(name = "product_order")
public class ProductOrder {

    @EmbeddedId
    private ProductOrderId id;

    @ManyToOne
    @MapsId("purchase_id")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Desk desk;

    public ProductOrder(Purchase purchase, Desk desk) {
        this.purchase = purchase;
        this.desk = desk;
    }

    public ProductOrder() {
    }
}
