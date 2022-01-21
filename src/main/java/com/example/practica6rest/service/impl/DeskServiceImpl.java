package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.repository.DeskRepository;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeskServiceImpl implements DeskService {

    private final DeskRepository deskRepository;

    @Autowired
    public DeskServiceImpl(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    @Override
    public Desk registry(Desk newDesk) {
        //@Todo logic
        Desk desk = deskRepository.save(newDesk);
        log.info(String.valueOf(desk));

        return desk;
    }
}
