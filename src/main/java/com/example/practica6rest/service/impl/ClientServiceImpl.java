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
import java.util.Optional;

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

    @Override
    public Client updateClient(Client newClient, Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setEmail(newClient.getEmail());
                    client.setOrder(newClient.getOrder());
                    client.setPhone_number(newClient.getPhone_number());
                    return clientRepository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientRepository.save(newClient);
                });
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
