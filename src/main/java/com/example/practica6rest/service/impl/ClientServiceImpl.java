package com.example.practica6rest.service.impl;

import com.example.practica6rest.dto.ClientDto;
import com.example.practica6rest.model.Client;
import com.example.practica6rest.repository.ClientRepository;
import com.example.practica6rest.service.ClientService;
import com.example.practica6rest.translator.ClientTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
    public ClientDto getById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");

        }
        log.info("Client with id: " + id + " retrieved successfully");
        Client client = clientRepository.getById(id);
        ClientDto clientDto = new ClientDto();
        new ClientTranslator().toDto(client, clientDto);
        return clientDto;
    }

    @Override
    public ResponseEntity<ClientDto> registry(ClientDto newClient) {
        if (clientRepository.existsByEmail(newClient.getEmail())) {
            log.info("Client with email: " + newClient.getEmail() + "is already exist");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Client client = new Client();
        new ClientTranslator().fromDto(newClient, client);
        client.setCreatedBy(newClient.getName());
        client.setUpdatedBy(newClient.getName());

        clientRepository.save(client);
        new ClientTranslator().toDto(client, newClient);

        log.info(String.valueOf(newClient));

        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @Override
    public List<ClientDto> getAll() {

        List<Client> clients = clientRepository.findAll();
        List<ClientDto> clientDtos = new ArrayList<>();

        for (Client client : clients) {
            ClientDto clientDto = new ClientDto();
            new ClientTranslator().toDto(client, clientDto);
            clientDtos.add(clientDto);
        }

        return clientDtos;
    }

    @Override
    public ClientDto update(ClientDto newClient, Long id) {

        return clientRepository.findById(id)
                .map(clientDto -> {
                    clientDto.setName(newClient.getName());
                    clientDto.setEmail(newClient.getEmail());
                    clientDto.setPhone_number(newClient.getPhone_number());
//                    clientDto.setUpdatedBy(SecurityContextHolder.get().getUser().getName());
                    log.info("Client with id: " + id + "is updated successfully");
                    Client client = clientRepository.save(clientDto);
                    ClientDto newClientDto = new ClientDto();
                    new ClientTranslator().toDto(client, newClientDto);
                    return newClientDto;
                })
                .orElseThrow(() -> new EntityNotFoundException("NO SUCH CLIENT"));
    }

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH CLIENT");     //Todo Exceptions
        }
        log.info("Client with id: " + id + " is successfully deleted");
        clientRepository.deleteById(id);
    }

}
