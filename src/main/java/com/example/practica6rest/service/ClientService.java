package com.example.practica6rest.service;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.model.Desk;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    Client getById(Long id);

    ResponseEntity<Client> registry(Client newClient);

    List<Client> getAll();

    Client update(Client newClient, Long id);

    void delete(Long id);
}
