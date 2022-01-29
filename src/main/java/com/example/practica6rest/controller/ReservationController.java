package com.example.practica6rest.controller;

import com.example.practica6rest.model.Reservation;
import com.example.practica6rest.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public List<Reservation> getPurchases() {
        return reservationService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<Reservation> createPurchase(@RequestBody Reservation newReservation) {
        return reservationService.registry(newReservation);
    }

    @GetMapping(value = "/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getById(id);
    }

    @PutMapping("/{id}")
    public Reservation updatePurchase(@RequestBody Reservation newReservation, @PathVariable Long id) {
        return reservationService.update(newReservation, id);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
