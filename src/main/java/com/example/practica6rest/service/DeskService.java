package com.example.practica6rest.service;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.model.Desk;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeskService {
    List<Desk> getAll();

    Desk getById(Long id);

    ResponseEntity<Desk> registry(Desk newDesk);

    void delete(Long id);

    Desk update(Desk newDesk, Long id);
}
