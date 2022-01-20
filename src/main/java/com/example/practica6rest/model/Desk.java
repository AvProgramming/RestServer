package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    public Desk(Integer number, Integer max_capacity) {
        this.number = number;
        this.max_capacity = max_capacity;
    }

    public Desk() {
    }

    @Override
    public String toString() {
        return "Desk{" +
                "id=" + id +
                ", number=" + number +
                ", max_capacity=" + max_capacity +
                '}';
    }
}
