package com.example.practica6rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RestDesk {

    @EmbeddedId
    private RestDeskId id;

    @ManyToOne
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @MapsId("deskId")
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @Column
    private Boolean status;

    public RestDesk(Restaurant restaurant, Desk desk, Boolean status) {
        this.restaurant = restaurant;
        this.desk = desk;
        this.status = status;
    }

    public RestDesk() {
    }
}
