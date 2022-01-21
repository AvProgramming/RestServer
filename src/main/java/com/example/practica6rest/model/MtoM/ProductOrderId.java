package com.example.practica6rest.model.MtoM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProductOrderId implements Serializable {

    @Column(name = "purchase_id")
    Long purchase_id;

    @Column(name = "product_id")
    Long product_id;
}
