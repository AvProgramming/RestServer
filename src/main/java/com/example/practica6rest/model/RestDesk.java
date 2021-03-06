package com.example.practica6rest.model;

import com.example.practica6rest.model.MtoM.RestDeskId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "rest_desk")
public class RestDesk {

    @EmbeddedId
    private RestDeskId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurant_id")
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference("restaurant_restDesks")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("desk_id")
    @JoinColumn(name = "desk_id")
    @JsonBackReference("desk_restDesks")
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
