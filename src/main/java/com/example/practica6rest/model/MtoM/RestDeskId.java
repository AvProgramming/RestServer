package com.example.practica6rest.model.MtoM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RestDeskId implements Serializable {

    @Column(name = "restaurant_id")
    Long restaurant_id;

    @Column(name = "desk_id")
    Long desk_id;
}
