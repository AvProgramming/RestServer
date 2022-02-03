package com.example.practica6rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "desk", fetch = FetchType.LAZY)
    @JsonManagedReference("desk_restDesks")
    private List<RestDesk> restDesks;

    @OneToMany(mappedBy = "desk", fetch = FetchType.LAZY)
    @JsonManagedReference("desk_reservations")
    private List<Reservation> reservations;

    public Desk(Integer number, Integer max_capacity) {
        this.number = number;
        this.max_capacity = max_capacity;
    }

    public Desk() {
    }
}
