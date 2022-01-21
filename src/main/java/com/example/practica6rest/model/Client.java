package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Integer phone_number;

    @OneToMany(mappedBy = "client")
    private List<FoodOrder> order;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservation;

    public Client(String name, String email, Integer phone_number) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public Client() {
    }
}
