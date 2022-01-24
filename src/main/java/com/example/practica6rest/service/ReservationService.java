package com.example.practica6rest.service;

import com.example.practica6rest.model.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    ResponseEntity<Reservation> registry(Reservation newReservation);

    List<Reservation> getAll();

    Reservation update(Reservation newReservation, Long id);

    void delete(Long id);
}
