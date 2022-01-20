package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<FoodOrder> order;

    public Client(String name, String email, Integer phone_number) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public Collection<FoodOrder> getOrder() {
        return order;
    }

    public void setOrder(Collection<FoodOrder> order) {
        this.order = order;
    }
}
