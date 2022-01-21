package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "desk")
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer number;

    @Column
    private Integer max_capacity;

    @OneToMany(mappedBy = "desk")
    private List<RestDesk> restDesks;

    @OneToMany(mappedBy = "desk")
    private List<Reservation> reservation;

    public Desk(Integer number, Integer max_capacity) {
        this.number = number;
        this.max_capacity = max_capacity;
    }

    public Desk() {
    }
}
