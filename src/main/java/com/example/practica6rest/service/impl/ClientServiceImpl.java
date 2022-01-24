package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Client;
import com.example.practica6rest.repository.ClientRepository;
import com.example.practica6rest.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Client getById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");

        }
        return clientRepository.getById(id);
    }

    @Override
    public ResponseEntity<Client> registry(Client newClient) {
        //TODO logic
        Client client = clientRepository.save(newClient);
        log.info(String.valueOf(client));

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @Override
    public List<Client> getAll() {
        //TODO logic
        return clientRepository.findAll();
    }

    @Override
    public Client update(Client newClient, Long id) {
        //TODO logic
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setEmail(newClient.getEmail());
                    client.setPhone_number(newClient.getPhone_number());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH CLIENT"));
    }

    @Override
    public void delete(Long id) {
        //TODO logic
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH CLIENT");     //Todo Exceptions
        }
        clientRepository.deleteById(id);
    }

}
