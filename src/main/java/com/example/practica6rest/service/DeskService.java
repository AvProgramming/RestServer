package com.example.practica6rest.service;

import com.example.practica6rest.model.Desk;

public interface DeskService {

    Desk getById(Long id);

    Desk registry(Desk desk);

    void delete(Long id);

    Desk update(Desk newDesk, Long id);
}
