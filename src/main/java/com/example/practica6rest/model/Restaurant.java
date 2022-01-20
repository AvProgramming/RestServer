package com.example.practica6rest.model;

import javax.persistence.*;

@Entity
public class Restaurant  {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
}
