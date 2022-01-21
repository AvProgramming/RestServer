package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.repository.ClientRepository;
import com.example.practica6rest.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<Client> registry(Client newClient) {
        Client client = clientRepository.save(newClient);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @Override
    public List<Client> getClient() {
        return clientRepository.findAll();
    }
}
