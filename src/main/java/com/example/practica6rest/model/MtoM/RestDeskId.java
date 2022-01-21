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
    private Long restaurant_id;

    @Column(name = "desk_id")
    private Long desk_id;
}
