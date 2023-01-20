package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.OtraInstitucion;
import uce.edu.ec.devengamiento.models.service.IOtraInstitucionService;

import java.util.List;

@RestController
public class OtraInstitucionRest {

    @Autowired
    private IOtraInstitucionService service;

    @GetMapping({"/otraInstitucion/listAll", "/otraInstitucion/listAll/"})
    public List<OtraInstitucion> findAll() {
        return service.findAll();
    }

    @GetMapping("/otraInstitucion/findById/{id}")
    public OtraInstitucion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/otraInstitucion/save", "/otraInstitucion/save/"})
    public void save(@RequestBody OtraInstitucion otraInstitucion) {
        service.save(otraInstitucion);
    }

    @DeleteMapping("/otraInstitucion/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/otraInstitucion/update/{id}")
    public void update(@PathVariable Long id, @RequestBody OtraInstitucion otraInstitucion) {
        service.update(id, otraInstitucion);
    }

}
