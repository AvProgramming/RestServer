package com.example.practica6rest.service;

import com.example.practica6rest.model.Desk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeskService {
    List<Desk> getAll();

    Desk getById(Long id);

    ResponseEntity<Desk> registry(Desk newDesk);

    void delete(Long id);

    Desk update(Desk newDesk, Long id);

//    Page findPaginated(Pageable pageable);

//    Page<Desk> findPaginated(int page, int size);
//    Page<Desk> findPaginated(int page, int size);
}
