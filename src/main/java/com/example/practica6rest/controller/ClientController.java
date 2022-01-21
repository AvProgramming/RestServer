package com.example.practica6rest.controller;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("clients")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @PostMapping("createClient")
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
        Client client = clientRepository.save(newClient);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
}
