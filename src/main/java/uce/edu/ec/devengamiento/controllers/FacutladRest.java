package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Facultad;
import uce.edu.ec.devengamiento.models.service.IFacultadService;

import java.util.List;

@RestController
public class FacutladRest {

    @Autowired
    private IFacultadService service;

    @GetMapping({"/facultad/listAll", "/facultad/listAll/"})
    public List<Facultad> findAll() {
        return service.findAll();
    }

    @GetMapping("/facultad/findByIdUniversidad/{idUniversidad}")
    public List<Facultad> findFacultadsByIdUniversidad(@PathVariable Long idUniversidad) {
        return service.findFacultadsByIdUniversidad(idUniversidad);
    }

    @GetMapping("/facultad/findById/{id}")
    public Facultad findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/facultad/save", "/facultad/save/"})
    public void save(@RequestBody Facultad facultad) {
        service.save(facultad);
    }

    @DeleteMapping("/facultad/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/facultad/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Facultad facultad) {
        service.update(id, facultad);
    }

}
