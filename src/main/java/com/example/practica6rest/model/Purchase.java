package com.example.practica6rest.model;

import com.example.practica6rest.model.enumeral.ProductType;
import com.example.practica6rest.model.enumeral.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date time;

    @Column
    private String content;

    @Column
    private Double total_price;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private PurchaseStatus type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
    private List<ProductPurchase> productPurchase;

    public Purchase(Date time, String content, Double total_price, PurchaseStatus type) {
        this.time = time;
        this.content = content;
        this.total_price = total_price;
        this.type = type;
    }

    public Purchase() {
    }
}
