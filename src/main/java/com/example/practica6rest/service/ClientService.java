package com.example.practica6rest.service;

import com.example.practica6rest.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ResponseEntity<Client> registry(Client client);

    List<Client> getClient();
}
