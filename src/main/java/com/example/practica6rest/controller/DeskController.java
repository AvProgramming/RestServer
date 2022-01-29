package com.example.practica6rest.controller;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/desks")
public class DeskController {

    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping(value = "/")
    public List<Desk> getDesks() {
        return deskService.getAll();
    }

    @PostMapping(value = "/")
    public ResponseEntity<Desk> createDesk(@RequestBody Desk newDesk) {
        return deskService.registry(newDesk);
    }

    @GetMapping(value = "/{id}")
    public Desk getDeskById(@PathVariable Long id) {
        return deskService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public Desk updateDesk(@RequestBody Desk newDesk, @PathVariable Long id) {
        return deskService.update(newDesk, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDesk(@PathVariable Long id) {
        deskService.delete(id);
    }

    @GetMapping(value = "/pagination/{page}/{size}")
    public Page<Desk> getDesksPageable(@PathVariable int page, @PathVariable int size) {
        Page<Desk> desks = deskService.findPaginated(page, size);
        return desks; //@TODO ResponseEntity
    }

    @GetMapping(value = "/pagination/{page}/{size}/{field}")
    public Page<Desk> getDesksPageableAndSorted(@PathVariable int page, @PathVariable int size, @PathVariable String field) {
        Page<Desk> desks = deskService.findPaginated(page, size, field);
        return desks; //@TODO ResponseEntity
    }

}
