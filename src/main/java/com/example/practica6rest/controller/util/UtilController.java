package com.example.practica6rest.controller.util;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.repository.DeskRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
@RestController
public class UtilController {

    private final DeskRepository deskRepository;

    @Autowired
    public UtilController(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    @RequestMapping("/json")
    public void json() {
        //get json data from resources
        URL urlDesks = this.getClass().getClassLoader().getResource("desks.json");

        if (urlDesks != null) {
            File jsonDesk = new File(urlDesks.getFile());

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<Desk> desks = objectMapper.readValue(jsonDesk, new TypeReference<List<Desk>>() {
                });

                deskRepository.saveAll(desks);

                log.info("All records saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("url is null");
        }

    }

}
