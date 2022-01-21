package com.example.practica6rest.controller;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DeskController {

    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @PostMapping(value = "/createDesk")
    public Desk create(@RequestBody Desk newDesk) {
        return deskService.registry(newDesk);
    }
}
