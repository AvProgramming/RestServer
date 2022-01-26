package com.example.practica6rest.controller;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeskController {

    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping("/desks")
    public List<Desk> getDesks() {
        return deskService.getAll();
    }

    @PostMapping(value = "/desks")
    public ResponseEntity<Desk> createDesk(@RequestBody Desk newDesk) {
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

//    @GetMapping(value = "/blogPageable")
//    public Page blogPageable(Pageable pageable) {
//        return deskService.findPaginated(pageable);
//    }

}
