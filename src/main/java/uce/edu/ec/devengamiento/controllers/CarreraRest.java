package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Carrera;
import uce.edu.ec.devengamiento.models.service.ICarreraService;

import java.util.List;

@RestController
public class CarreraRest {

    @Autowired
    private ICarreraService service;

    @GetMapping({"/carrera/listAll", "/carrera/listAll/"})
    public List<Carrera> findAll() {
        return service.findAll();
    }

    @GetMapping("/carrera/findByIdFacultad/{idFacultad}")
    public List<Carrera> findCarreraByIdFacultad(@PathVariable Long idFacultad) {
        return service.findCarrerasByIdFacultad(idFacultad);
    }

    @GetMapping("/carrera/findById/{id}")
    public Carrera findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/carrera/save", "/carrera/save/"})
    public void save(@RequestBody Carrera carrera) {
        service.save(carrera);
    }

    @DeleteMapping("/carrera/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/carrera/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Carrera carrera) {
        service.update(id, carrera);
    }

}
