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

    @Column(name = "product_order_id")
    Long product_order_id;

    @Column(name = "product_id")
    Long product_id;
}
