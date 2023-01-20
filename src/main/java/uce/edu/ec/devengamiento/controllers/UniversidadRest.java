package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Universidad;
import uce.edu.ec.devengamiento.models.service.IUniversidadService;

import java.util.List;

@RestController
public class UniversidadRest {

    @Autowired
    private IUniversidadService service;

    @GetMapping({"/universidad/listAll", "/universidad/listAll/"})
    public List<Universidad> findAll() {
        return service.findAll();
    }

    @GetMapping("/universidad/findById/{id}")
    public Universidad findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/universidad/save", "/universidad/save/"})
    public void save(@RequestBody Universidad universidad) {
        service.save(universidad);
    }

    @DeleteMapping("/universidad/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/universidad/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Universidad universidad) {
        service.update(id, universidad);
    }

}
