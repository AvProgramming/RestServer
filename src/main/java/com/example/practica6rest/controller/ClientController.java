package com.example.practica6rest.controller;

import com.example.practica6rest.dto.ClientDto;
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
    public List<ClientDto> getClients() {
        return clientService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto newClient) {
        return clientService.registry(newClient);
    }

    @GetMapping(value = "/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PutMapping("/{id}")
    public ClientDto updateClient(@RequestBody ClientDto client, @PathVariable Long id) {
        return clientService.update(client, id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }
}
