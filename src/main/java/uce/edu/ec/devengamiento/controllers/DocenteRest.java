package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.service.IDocenteService;

import java.util.List;

@RestController
public class DocenteRest {

    @Autowired
    private IDocenteService service;

    @GetMapping({"/docente/listAll", "/docente/listAll/"})
    public List<Docente> listAll() {
        return service.findAll();
    }

    @GetMapping("/docente/listById/{id}")
    public Docente listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/docente/save", "/docente/save/"})
    public void save(@RequestBody Docente docente) {
        service.save(docente);
    }

    @DeleteMapping({"/docente/deleteById/{id}"})
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/docente/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Docente docente) {
        service.update(id, docente);
    }

}
