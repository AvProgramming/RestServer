package com.example.practica6rest.controller;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ClientController {

    final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("clients")
    public List<Client> getClients() {
        return clientService.getClient();
    }

    @PostMapping("createClient")
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
       return clientService.registry(newClient);
    }
}
