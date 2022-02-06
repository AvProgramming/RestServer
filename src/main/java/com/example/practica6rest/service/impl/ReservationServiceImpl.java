package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Reservation;
import com.example.practica6rest.repository.ReservationRepository;
import com.example.practica6rest.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation getById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");

        }
        log.info("Reservation with id: " + id + " retrieved successfully");
        return reservationRepository.getById(id);
    }

    @Override
    public ResponseEntity<Reservation> registry(Reservation newReservation) {
        //TODO logic
        Reservation reservation = reservationRepository.save(newReservation);
        log.info(String.valueOf(reservation));

        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Reservation newReservation, Long id) {
        //TODO logic
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setTime(newReservation.getTime());
                    reservation.setDesk(newReservation.getDesk());
                    reservation.setRestaurant(newReservation.getRestaurant());
                    reservation.setClient(newReservation.getClient());
                    log.info("Reservation is updated successfully " + newReservation);
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH RESERVATION"));
    }

    @Override
    public void delete(Long id) {
        //TODO logic
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH RESERVATION");
        }
        log.info("Reservation with id: " + id + " is successfully deleted");
        reservationRepository.deleteById(id);
    }
}

