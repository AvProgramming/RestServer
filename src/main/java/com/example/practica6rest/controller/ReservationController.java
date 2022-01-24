package com.example.practica6rest.controller;

import com.example.practica6rest.model.Reservation;
import com.example.practica6rest.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public List<Reservation> getPurchases() {
        return reservationService.getAll();
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createPurchase(@RequestBody Reservation newReservation) {
        return reservationService.registry(newReservation);
    }

    @PutMapping("/reservations/{id}")
    public Reservation updatePurchase(@RequestBody Reservation newReservation, @PathVariable Long id) {
        return reservationService.update(newReservation, id);
    }

    @DeleteMapping("/reservations/{id}")
    public void deletePurchase(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
