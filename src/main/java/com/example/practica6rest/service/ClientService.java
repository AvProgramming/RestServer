package com.example.practica6rest.service;

import com.example.practica6rest.dto.ClientDto;
import com.example.practica6rest.model.Client;
import com.example.practica6rest.model.Desk;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ClientDto getById(Long id);

    ResponseEntity<ClientDto> getByEmail(String email);

    ResponseEntity<ClientDto> registry(ClientDto newClient);

    List<ClientDto> getAll();

    ClientDto update(ClientDto newClient, Long id);

    void delete(Long id);
}
