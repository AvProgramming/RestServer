package com.example.practica6rest.controller;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> getClients() {
        return clientService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
        return clientService.registry(newClient);
    }

    @GetMapping(value = "/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@RequestBody Client client, @PathVariable Long id) {
        return clientService.update(client, id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }
}
