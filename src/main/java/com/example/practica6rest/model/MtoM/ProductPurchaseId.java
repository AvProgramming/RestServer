package com.example.practica6rest.model.MtoM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProductPurchaseId implements Serializable {

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "purchase_id")
    private Long purchase_id;

}
