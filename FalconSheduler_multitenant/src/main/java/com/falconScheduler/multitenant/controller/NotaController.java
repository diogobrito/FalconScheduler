package com.falconScheduler.multitenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.falconScheduler.multitenant.model.Nota;
import com.falconScheduler.multitenant.repository.NotaRepository;

import java.util.Iterator;

@RestController
@RequestMapping(value = "/nota")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping
    public Iterator<Nota> getAll() {
        return notaRepository.findAll().iterator();
    }

    @PostMapping
    public void save(@RequestBody Nota nota) {
        notaRepository.save(nota);
    }
}
