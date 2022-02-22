package com.example.practica6rest.translator;

import com.example.practica6rest.dto.ClientDto;
import com.example.practica6rest.model.Client;

public final class ClientTranslator {

    public void toDto (Client source, ClientDto destination){
        destination.setId(source.getId());
        destination.setName(source.getName());
        destination.setEmail(source.getEmail());
        destination.setPhone_number(source.getPhone_number());
    }

    public void fromDto (ClientDto source, Client destination){
        destination.setId(source.getId());
        destination.setName(source.getName());
        destination.setEmail(source.getEmail());
        destination.setPhone_number(source.getPhone_number());
    }
}
