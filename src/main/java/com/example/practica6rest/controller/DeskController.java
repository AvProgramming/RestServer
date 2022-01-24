package com.example.practica6rest.controller;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DeskController {

    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @PostMapping(value = "/desks")
    public Desk createDesk(@RequestBody Desk newDesk) {
        return deskService.registry(newDesk);
    }

    @GetMapping(value = "/desks/{id}")
    public Desk getDeskById(@PathVariable Long id) {
        return deskService.getById(id);
    }

    @PutMapping(value = "/desks/{id}")
    public Desk updateDesk(@RequestBody Desk newDesk, @PathVariable Long id) {
        return deskService.update(newDesk, id);
    }

    @DeleteMapping(value = "/desks/{id}")
    public void deleteDesk(@PathVariable Long id) {
        deskService.delete(id);
    }

}
