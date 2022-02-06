package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.repository.DeskRepository;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class DeskServiceImpl implements DeskService {

    private final DeskRepository deskRepository;

    @Autowired
    public DeskServiceImpl(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    @Override
    public List<Desk> getAll() {
        return deskRepository.findAll();
    }

    @Override
    public Desk getById(Long id) {
        //@Todo logic
        if (!deskRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");
        }
        log.info("Desk with id: " + id + " retrieved successfully");
        return deskRepository.getById(id);
    }

    @Override
    public ResponseEntity<Desk> registry(Desk newDesk) {
        //@Todo logic
        Desk desk = deskRepository.save(newDesk);
        log.info(String.valueOf(desk));

        return new ResponseEntity<>(desk, HttpStatus.CREATED);
    }

    @Override
    public void delete(Long id) {

        if (!deskRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");     //@Todo Exceptions
        }
        log.info("Desk with id: " + id + " is successfully deleted");
        deskRepository.deleteById(id);
    }

    @Override
    public Desk update(Desk newDesk, Long id) {

        if (!deskRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");       //@Todo Exceptions
        }

        Desk oldDesk = deskRepository.getById(id);
        oldDesk.setNumber(newDesk.getNumber());
        oldDesk.setMax_capacity(newDesk.getMax_capacity());

        log.info("Desk: " + newDesk + " updated successfully");

        return deskRepository.saveAndFlush(oldDesk);
    }

    @Override
    public Page<Desk> findPaginated(int page, int size) {
        log.info("Retrieved paginated desks successfully");
        return deskRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Desk> findPaginated(int page, int size, String field) {
        log.info("Retrieved paginated desks successfully");
        return deskRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(field)));
    }

}
