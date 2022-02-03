package com.example.practica6rest.model;

import com.example.practica6rest.model.enumeral.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonManagedReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
    @JsonBackReference
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
