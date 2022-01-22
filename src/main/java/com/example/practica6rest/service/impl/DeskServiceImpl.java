package com.example.practica6rest.service.impl;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.repository.DeskRepository;
import com.example.practica6rest.service.DeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class DeskServiceImpl implements DeskService {

    private final DeskRepository deskRepository;

    @Autowired
    public DeskServiceImpl(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    @Override
    public Desk getById(Long id) {
        //@Todo logic
            if (!deskRepository.existsById(id)) {
                throw new EntityNotFoundException("NO SUCH DESK");

            }
        return deskRepository.getById(id);

    }

    @Override
    public Desk registry(Desk newDesk) {
        //@Todo logic
        Desk desk = deskRepository.save(newDesk);
        log.info(String.valueOf(desk));

        return desk;
    }

    @Override
    public void delete(Long id)  {

        if (!deskRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");     //@Todo Exceptions
        }
        deskRepository.deleteById(id);
    }

    @Override
    public Desk update(Desk newDesk, Long id)  {

        if (!deskRepository.existsById(id)) {
            throw new EntityNotFoundException("NO SUCH DESK");       //@Todo Exceptions
        }

        Desk oldDesk = deskRepository.getById(id);
        oldDesk.setNumber(newDesk.getNumber());
        oldDesk.setMax_capacity(newDesk.getMax_capacity());

        return deskRepository.saveAndFlush(oldDesk);
    }
}
