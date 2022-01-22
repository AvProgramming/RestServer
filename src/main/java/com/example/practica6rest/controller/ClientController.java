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
public class ClientController {

    final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.getClient();
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
       return clientService.registry(newClient);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@RequestBody Client client, @PathVariable Long id) {
       return clientService.updateClient(client, id);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
